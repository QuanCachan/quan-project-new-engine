package fr.henix.squash.backbone.engine.wrapper;

import fr.henix.squash.framework.components.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author qtran
 */
public abstract class ConfiguredComponentHandler {

    /**
     * Method to send the unwrapped configuration into the wrapped component.
     *
     * @param unwrappedConfig the configuration resources.
     */
    protected abstract void addConfigurationToWrapped(Collection<Resource<?>> unwrappedConfig);

    /**
     * Method to unwrap a configuration for the handled component.
     *
     * @param config the configuration.
     */
    public void addConfiguration(Collection<ResourceWrapper> config) {
        Collection<Resource<?>> unwrappedConfig = new ArrayList<Resource<?>>();
        for (ResourceWrapper confElementWrapper : config) {
            unwrappedConfig.add(confElementWrapper.unwrap());
        }
        addConfigurationToWrapped(unwrappedConfig);
    }
}
