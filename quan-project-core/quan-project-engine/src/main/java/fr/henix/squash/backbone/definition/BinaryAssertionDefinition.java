
package fr.henix.squash.backbone.definition;

import fr.henix.squash.backbone.engine.wrapper.Nature;
import fr.henix.squash.framework.components.BinaryAssertion;

/**
 *
 * @author qtran
 */
public class BinaryAssertionDefinition extends	EngineComponentDefinition<BinaryAssertion<?,?>> {
    
    public BinaryAssertionDefinition(Nature firstNature, Nature secondNature, Nature category, Class<BinaryAssertion<?, ?>> componentClass) {
        super(firstNature, secondNature, category, componentClass);
    }
    
}
