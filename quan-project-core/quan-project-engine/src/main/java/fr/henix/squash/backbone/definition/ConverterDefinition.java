
package fr.henix.squash.backbone.definition.engine;

import fr.henix.squash.backbone.engine.wrapper.Nature;
import fr.henix.squash.framework.components.ResourceConverter;

/**
 *
 * @author qtran
 */
public class ConverterDefinition extends EngineComponentDefinition<ResourceConverter<?,?>>{
    
    public ConverterDefinition(Nature firstNature, Nature secondNature, Nature category, Class<ResourceConverter<?, ?>> componentClass) {
        super(firstNature, secondNature, category, componentClass);
    }
    
}
