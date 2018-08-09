package fr.henix.squash.backbone.engine.wrapper;

import fr.henix.squash.backbone.tools.ReflectionUtils;
import fr.henix.squash.framework.components.BinaryAssertion;
import fr.henix.squash.framework.components.Resource;
import fr.henix.squash.framework.exception.BinaryAssertionFailedException;
import java.util.Collection;

/**
 * A BinaryAssertionHandler wraps an actual instance of {@link BinaryAssertion}
 * and manage the metadata.
 *
 * @author qtran
 */
public class BinaryAssertionHandler extends ConfiguredComponentHandler {

    private static final String ACTUAL_SETTER_METHOD = "setActualResult";
    private static final String EXPECTED_SETTER_METHOD = "setExpectedResult";

    private ReflectionUtils reflector = new ReflectionUtils();

    private Nature actualNature;
    private Nature expectedNature;
    private Nature assertionCategory;

    private BinaryAssertion<?, ?> wrappedAssertion;
    private Class<?> actualType;
    private Class<?> expectedType;

    /* ************** ctors ******************** */
    public BinaryAssertionHandler() {
        super();
    }

    public BinaryAssertionHandler(Nature actualNature, Nature expectedNature,
            Nature assertionCategory, BinaryAssertion<?, ?> wrappedAssertion) {

        super();
        this.actualNature = actualNature;
        this.expectedNature = expectedNature;
        this.assertionCategory = assertionCategory;
        this.wrappedAssertion = wrappedAssertion;
        Class<?> assertionType = wrappedAssertion.getClass();
        Class<?>[] parameters = reflector.getGenericTypes(assertionType, BinaryAssertion.class);
        this.actualType = parameters[0];
        this.expectedType = parameters[1];
    }

    /* ************** metadata ***************** */
    public Nature getActualResultNature() {
        return actualNature;
    }

    public void setActualResultNature(Nature nature) {
        this.actualNature = nature;
    }

    public Nature getExpectedResultNature() {
        return expectedNature;
    }

    public void setExpectedResultNature(Nature nature) {
        this.expectedNature = nature;
    }

    public Nature getAssertionCategory() {
        return assertionCategory;
    }

    public void setAssertionCategory(Nature category) {
        this.assertionCategory = category;
    }

    public void wrapAssertion(BinaryAssertion<?, ?> assertion) {
        this.wrappedAssertion = assertion;
    }

    /* ******************* wrapped methods *************** */
    public void setActualResult(ResourceWrapper actual) {
        Resource<?> resource = actual.unwrap();
        reflector.invoke(wrappedAssertion, ACTUAL_SETTER_METHOD, actualType, resource);
    }

    public void setExpectedResult(ResourceWrapper expected) {
        Resource<?> resource = expected.unwrap();
        reflector.invoke(wrappedAssertion, EXPECTED_SETTER_METHOD, expectedType, resource);
    }

    public void test() throws BinaryAssertionFailedException {
        wrappedAssertion.test();
    }

    @Override
    protected void addConfigurationToWrapped(Collection<Resource<?>> unwrappedConfig) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
