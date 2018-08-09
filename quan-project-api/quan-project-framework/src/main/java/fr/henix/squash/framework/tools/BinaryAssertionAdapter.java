package fr.henix.squash.framework.tools;

import fr.henix.squash.framework.annotations.TABinaryAssertion;
import fr.henix.squash.framework.components.BinaryAssertion;
import fr.henix.squash.framework.exception.BrokenTestException;

/**
 *
 * @author qtran
 */
public class BinaryAssertionAdapter implements ComponentAdapter {

    private final BinaryAssertion<?, ?> asser;

    public BinaryAssertionAdapter(BinaryAssertion<?, ?> asser) {
        this.asser = asser;
    }

    @Override
    public String getNature() {
        TABinaryAssertion annotation = asser.getClass().getAnnotation(TABinaryAssertion.class);
        if (annotation == null) {
            throw new BrokenTestException("BinaryAssertion class " + asser.getClass() + " is not annotated @TABinaryAssertion and will not work!");
        } else {
            return annotation.value();
        }
    }

}
