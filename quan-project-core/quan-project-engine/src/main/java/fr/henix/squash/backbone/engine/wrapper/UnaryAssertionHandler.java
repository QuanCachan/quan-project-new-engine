package fr.henix.squash.backbone.engine.wrapper;

import fr.henix.squash.backbone.tools.ReflectionUtils;
import fr.henix.squash.framework.components.Resource;
import fr.henix.squash.framework.components.UnaryAssertion;
import fr.henix.squash.framework.exception.AssertionFailedException;
import java.util.Collection;

/**
 * An UnaryAssertionHandler will wrap an actual instance of
 * {@link UnaryAssertion} and manage the metadata.
 *
 * @author qtran
 */
public class UnaryAssertionHandler extends ConfiguredComponentHandler{

    private static final String SETTER_METHOD = "setActualResult";

    private ReflectionUtils reflector = new ReflectionUtils();

    private Nature actualNature;
    private Nature assertionCategory;

    private UnaryAssertion<?> wrappedAssertion;
    private Class<?> actualType;

    /* ************** ctors ******************** */
    public UnaryAssertionHandler() {
        super();
    }

    public UnaryAssertionHandler(Nature actualNature, Nature assertionCategory, UnaryAssertion<?> assertion) {
        this.actualNature = actualNature;
        this.assertionCategory = assertionCategory;
        this.wrappedAssertion = assertion;
        Class<?> assertionClass = assertion.getClass();
        Class<?>[] parameters = reflector.getGenericTypes(assertionClass, UnaryAssertion.class);
        this.actualType = parameters[0];
    }

    /* ************** metadata ***************** */
    public Nature getActualResultNature() {
        return actualNature;
    }

    public void setActualResultNature(Nature nature) {
        this.actualNature = nature;
    }

    public Nature getAssertionCategory() {
        return assertionCategory;
    }

    public void setAssertionCategory(Nature category) {
        this.assertionCategory = category;
    }

    public void wrapAssertion(UnaryAssertion<?> assertion) {
        this.wrappedAssertion = assertion;
    }

    /* ******************* wrapped methods *************** */
    public void setActualResult(ResourceWrapper actual) {
        Resource<?> resource = actual.unwrap();
        reflector.invoke(wrappedAssertion, SETTER_METHOD, actualType, resource);
    }

    public void test() throws AssertionFailedException {
        wrappedAssertion.test();
    }

    @Override
    protected void addConfigurationToWrapped(
            Collection<Resource<?>> unwrappedConfig) {
        wrappedAssertion.addConfiguration(unwrappedConfig);
    }
}
