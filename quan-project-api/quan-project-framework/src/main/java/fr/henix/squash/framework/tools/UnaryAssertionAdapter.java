package fr.henix.squash.framework.tools;

import fr.henix.squash.framework.annotations.TAUnaryAssertion;
import fr.henix.squash.framework.components.UnaryAssertion;
import fr.henix.squash.framework.exception.BrokenTestException;

/**
 *
 * @author qtran
 */
public class UnaryAssertionAdapter implements ComponentAdapter {

    private final UnaryAssertion<?> asser;

    public UnaryAssertionAdapter(UnaryAssertion<?> asser) {
        this.asser = asser;
    }

    @Override
    public String getNature() {
        TAUnaryAssertion annotation = asser.getClass().getAnnotation(TAUnaryAssertion.class);
        if (annotation == null) {
            throw new BrokenTestException("UnaryAssertion class " + asser.getClass() + " is not annotated @TAUnaryAssertion and will not work!");
        } else {
            return annotation.value();
        }
    }

}
