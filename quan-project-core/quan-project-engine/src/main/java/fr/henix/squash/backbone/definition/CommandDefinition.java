
package fr.henix.squash.backbone.definition.engine;

import fr.henix.squash.backbone.engine.wrapper.Nature;
import fr.henix.squash.framework.components.Command;

/**
 *
 * @author qtran
 */
public class CommandDefinition extends EngineComponentDefinition<Command<?,?>>{
    
    private Nature resultNature;
    
    public CommandDefinition(Nature first, Nature second, Nature category, Nature resultNature, Class<Command<?, ?>> clazz) {
        super(first, second, category, clazz);
        this.resultNature = resultNature;
    }

    public Nature getResultNature() {
        return resultNature;
    }
    
}
