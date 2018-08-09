package fr.henix.squash.backbone.engine;

/**
 * An AssertionFindSettings contains parameters that will help to find an
 * assertion. At least the assertion identifier and the actual result name must
 * be supplied. If no expectedResult is supplied, then it is assumed that we are
 * looking for an unary assertion.
 *
 * @author qtran
 */
public interface AssertionFindSettings {

    String getActualResultNatureName();

    String getExpectedResultNatureName();

    String getAssertionCategory();

    void setActualResultNatureName(String name);

    void setExpectedResultNatureName(String name);

    void setAssertionCategory(String name);
}
