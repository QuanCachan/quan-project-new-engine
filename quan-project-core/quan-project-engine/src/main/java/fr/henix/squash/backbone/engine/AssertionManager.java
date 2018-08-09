package fr.henix.squash.backbone.engine;

import fr.henix.squash.backbone.definition.BinaryAssertionDefinition;
import fr.henix.squash.backbone.definition.engine.UnaryAssertionDefinition;
import fr.henix.squash.backbone.engine.wrapper.BinaryAssertionHandler;
import fr.henix.squash.backbone.engine.wrapper.Nature;
import fr.henix.squash.backbone.engine.wrapper.UnaryAssertionHandler;
import java.util.Collection;

/**
 *
 * @author qtran
 */
public interface AssertionManager {

    /**
     * Will add a definition for a BinaryAssertion. Will refuse to perform this
     * operation if a factory having the same actual result nature, expected
     * result nature and identifier is already present.
     *
     * @param definition
     */
    void addAssertionDefinition(BinaryAssertionDefinition definition);

    void removeAssertionDefinition(BinaryAssertionDefinition definition);

    void removeAssertionDefinition(Nature actualNature, Nature expectedNature, Nature category);

    /**
     * Same for unary assertion.
     *
     */
    void addAssertionDefinition(UnaryAssertionDefinition definition);

    void removeAssertionDefinition(UnaryAssertionDefinition definition);

    void removeAssertionDefinition(Nature actualNature, Nature category);

    /**
     * <p>
     * Depending on the given parameter, will return a list of new BINARY
     * Assertion instances (with empty configuration). Each parameter accepts
     * null, but the more the caller provides the more precise will be the
     * result. Theoretically if the user supplies all the arguments the returned
     * list shall be of size 0 of 1.
     * </p>
     *
     *
     * @param actualNature
     * @param expectedNature
     * @param category
     * @return a Collection of Assertions fitting the description (never null).
     */
    Collection<BinaryAssertionHandler> getAllBinaryAssertions(Nature actualNature, Nature expectedNature, Nature category);

    Collection<BinaryAssertionHandler> getAllBinaryAssertionsByName(String actualNatureName, String expectedNatureName, String category);

    /**
     * <p>
     * Depending on the given parameter, will return a list of new UNARY
     * Assertion instances (with empty configuration). Each parameter accepts
     * null, but the more the caller provides the more precise will be the
     * result. Theoretically if the user supplies all the arguments the returned
     * list shall be of size 0 of 1.
     * </p>
     *
     *
     * @param actualNature
     * @param category
     * @return a Collection of Assertions fitting the description (never null).
     */
    Collection<UnaryAssertionHandler> getAllUnaryAssertions(Nature actualNature, Nature category);

    Collection<UnaryAssertionHandler> getAllUnaryAssertionsByName(String actualNatureName, String categoryName);
}
