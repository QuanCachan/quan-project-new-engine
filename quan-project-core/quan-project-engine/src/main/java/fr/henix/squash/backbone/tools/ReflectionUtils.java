package fr.henix.squash.backbone.tools;

import fr.henix.squash.backbone.exception.EngineInitException;
import fr.henix.squash.backbone.exception.EngineRuntimeException;
import fr.henix.squash.backbone.exception.MissingAnnotationException;
import fr.henix.squash.framework.annotations.TABinaryAssertion;
import fr.henix.squash.framework.annotations.TACommand;
import fr.henix.squash.framework.annotations.TARepository;
import fr.henix.squash.framework.annotations.TARepositoryCreator;
import fr.henix.squash.framework.annotations.TAResource;
import fr.henix.squash.framework.annotations.TAResourceConverter;
import fr.henix.squash.framework.annotations.TATarget;
import fr.henix.squash.framework.annotations.TATargetCreator;
import fr.henix.squash.framework.annotations.TAUnaryAssertion;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * That class is just what the name says it is. It is tailormade for our modest
 * needs. Also note that it could have been a static library yet it was made
 * instanciable : that way the class is easier to mock when testing other
 * classes that use it.
 *
 * @author qtran
 */
@SuppressWarnings("deprecation")
public class ReflectionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtils.class);

    private static final List<Class<? extends Annotation>> META_ENGINE_COMPONENTS;

    private static final List<Class<? extends Annotation>> META_RESOURCE_TYPE;

    static {
        List<Class<? extends Annotation>> meta = new ArrayList<Class<? extends Annotation>>(6);

        meta.add(TAUnaryAssertion.class);
        meta.add(TABinaryAssertion.class);
        meta.add(TACommand.class);
        meta.add(TAResourceConverter.class);
        meta.add(TARepositoryCreator.class);
        meta.add(TATargetCreator.class);

        META_ENGINE_COMPONENTS = Collections.unmodifiableList(meta);

        List<Class<? extends Annotation>> remeta = new ArrayList<Class<? extends Annotation>>(3);

        remeta.add(TAResource.class);
        remeta.add(TATarget.class);
        remeta.add(TARepository.class);

        META_RESOURCE_TYPE = Collections.unmodifiableList(remeta);
    }

    /* 
	 * our framework uses parameterized interfaces. We need to fetch that
	 * interface and the concrete types that were used to parameterize it in the inspectedClass.
     */
    public Class<?>[] getGenericTypes(Class<?> inspectedClass, Class<?> searchedInterface) {

        Type[] types = inspectedClass.getGenericInterfaces();

        Type interfaceAsType = findTypeMatchingClass(types, searchedInterface);

        if (interfaceAsType == null) {
            String message = "Squash TA engine (init) : class '" + inspectedClass + "' does not implement '" + searchedInterface + "'.";
            message += "Please contact Squash TA dev team as something is obviously broken in the framework engine.";
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(message);
            }
            throw new EngineInitException(message);
        }

        Type[] actualTypes = ((ParameterizedType) interfaceAsType).getActualTypeArguments();
        Class<?>[] result = new Class<?>[actualTypes.length];
        int counter = 0;

        for (Type type : actualTypes) {
            result[counter++] = getClassFromType(type);
        }

        return result;
    }

    private Type findTypeMatchingClass(Type[] types, Class<?> matchingClass) {
        for (Type type : types) {
            Class<?> typeAsClass = getClassFromType(type);
            if ((typeAsClass != null) && (typeAsClass.equals(matchingClass))) {
                return type;
            }
        }
        return null;

    }

    //except from http://www.artima.com/weblogs/viewpost.jsp?thread=208860
    //returns null if the given type is not an instance of ParameterizedType (since that's what we are looking for).
    private Class<?> getClassFromType(Type type) {
        Class<?> classForType = null;
        if (type instanceof Class) {
            classForType = (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            classForType = getClassFromType(((ParameterizedType) type).getRawType());
        }
        return classForType;
    }

    /**
     * Returns the class of the result returned when applying Method named
     * 'methodName' of Class 'inspectedClass'. The said method takes no
     * argument.
     *
     * @param inspectedClass
     * @param methodName
     * @return the Class of the result returned by that method
     */
    public Class<?> getReturnType(Class<?> inspectedClass, String methodName) {
        return getReturnType(inspectedClass, methodName, new Class<?>[0]);
    }

    /**
     * Returns the class of the result returned when applying Method named
     * 'methodName' of Class 'inspectedClass', which arguments are of classes
     * supplied in 'argTypes' in that order.
     *
     * @param inspectedClass
     * @param methodName
     * @param argTypes
     * @return the Class of the result returned by that method
     */
    public Class<?> getReturnType(Class<?> inspectedClass, String methodName, Class<?>[] argTypes) {
        try {
            Method method = inspectedClass.getMethod(methodName, argTypes);
            return method.getReturnType();
        } catch (SecurityException e) {
            String message = makeSecurityExceptionMessage(inspectedClass, methodName, argTypes);
            LOGGER.error(message, e);
            throw new EngineRuntimeException(message, e);
        } catch (NoSuchMethodException e) {
            String message = makeNoSuchMethodExceptionMessage(inspectedClass, methodName, argTypes);
            LOGGER.error(message, e);
            throw new EngineRuntimeException(message, e);
        }
    }

    private String makeSecurityExceptionMessage(Object owner, String methodName, Object argument) {

        StringBuilder builder = new StringBuilder();

        String message = errorMessagePreamble(owner, methodName, argument);
        builder.append(message);

        builder.append("is out of reach. Contact the developper who coded that class "
                + "and tell him to make it fully public.");

        return builder.toString();

    }

    private String errorMessagePreamble(Object owner, String methodName, Object argument) {

        StringBuilder builder = new StringBuilder();

        builder.append("Test Automation Engine error (non SUT) : method '");
        builder.append(methodName);
        builder.append("' from engine component of class '");
        builder.append(owner.getClass().getName());
        builder.append("' ");
        if (argument != null) {
            builder.append("with argument '").append(argument.getClass().getName()).append("' ");
        }

        return builder.toString();

    }

    private String makeNoSuchMethodExceptionMessage(Object owner, String methodName, Object argument) {

        StringBuilder builder = new StringBuilder();

        String message = errorMessagePreamble(owner, methodName, argument);
        builder.append(message);

        builder.append(" was not found. Contact the developper who coded that class "
                + "and tell him to make fully public.");

        return builder.toString();

    }

    // *********************** generic annotation processing ************************
    public String getEngineComponentValue(Class<?> clazz) {
        String value = getMetaAnnotationType(clazz, META_ENGINE_COMPONENTS);
        if (value == null) {
            String message = "Squash TA engine error (init) : @EngineComponent not found on class '" + clazz.getName() + "'. ";
            message += "Please contact the culprit and tell him he MUST annotate the class.";

            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(message);
            }

            throw new MissingAnnotationException(message);
        } else {
            return value;
        }
    }

    // ********************** dedicated annotations processing (since 1.5) ************
    private String getMetaAnnotationType(Class<?> clazz, Collection<Class<? extends Annotation>> annotationSet) {
        for (Class<? extends Annotation> annotType : annotationSet) {
            Annotation annotInstance = clazz.getAnnotation(annotType);
            if (annotInstance != null) {
                return (String) invoke(annotInstance, "value", null, null);
            }
        }
        return null;
    }

    public String getEngineComponentValue(Object object) {
        return getEngineComponentValue(object.getClass());
    }

    /**
     * Method to invoke by name a single- or no- argument instance method
     * method.
     *
     * @param owner name of the instance on which to invoke the method.
     * @param methodName name of the method to invoke.
     * @param argumentType type of the argument in the method signature (if
     * any), null if the method is a no-argument method.
     * @param argument an object (not an array !). May be null
     * @return the result of the method, null if its return type is
     * <code>void</code>.
     */
    public Object invoke(Object owner, String methodName, Class<?> argumentType, Object argument) {
        try {

            Method method;
            if (argument != null) {
                method = owner.getClass().getMethod(methodName, argumentType);
                return method.invoke(owner, argument);
            } else {
                method = owner.getClass().getMethod(methodName, (Class<?>[]) null);
                return method.invoke(owner, (Object[]) null);
            }

        } catch (SecurityException e) {
            String message = makeSecurityExceptionMessage(owner, methodName, argument);
            LOGGER.error(message, e);
            throw new EngineRuntimeException(message, e);
        } catch (NoSuchMethodException e) {
            String message = makeNoSuchMethodExceptionMessage(owner, methodName, argument);
            LOGGER.error(message, e);
            throw new EngineRuntimeException(message, e);
        } catch (IllegalArgumentException e) {
            String message = makeIllegalArgumentExceptionMessage(owner, methodName, argument);
            LOGGER.error(message, e);
            throw new EngineRuntimeException(message, e);
        } catch (IllegalAccessException e) {
            String message = makeIllegalAccessExceptionMessage(owner, methodName, argument);
            LOGGER.error(message, e);
            throw new EngineRuntimeException(message, e);
        } catch (InvocationTargetException e) {//NOSONAR - see below
            String message = makeInvocationTargetExceptionMessage(owner, methodName, argument);
            LOGGER.error(message, e.getCause());//true, here we throw out the InvocationTargetException because only its cause is meaningful
            throw new EngineRuntimeException(message, e.getCause());//NOSONAR - see above
        }
    }

    //seriously in the specific use of this class this situation is impossible.
    private String makeIllegalArgumentExceptionMessage(Object owner, String methodName, Object argument) {

        StringBuilder builder = new StringBuilder();

        String message = errorMessagePreamble(owner, methodName, argument);
        builder.append(message);

        builder.append(" should have worked but did not. Please contact the tech team and tell it to change the jvm.");

        return builder.toString();
    }

    private String makeIllegalAccessExceptionMessage(Object owner, String methodName, Object argument) {

        StringBuilder builder = new StringBuilder();

        String message = errorMessagePreamble(owner, methodName, argument);
        builder.append(message);

        builder.append(" is out of reach. Contact the developper who coded that class "
                + "and tell him to make fully public.");

        return builder.toString();

    }

    private String makeInvocationTargetExceptionMessage(Object owner, String methodName, Object argument) {

        StringBuilder builder = new StringBuilder();

        String message = errorMessagePreamble(owner, methodName, argument);
        builder.append(message);

        builder.append(" has thrown an exception.");

        return builder.toString();

    }

}
