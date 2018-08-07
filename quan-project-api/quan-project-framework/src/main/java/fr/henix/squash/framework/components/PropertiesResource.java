
package fr.henix.squash.framework.components;

import fr.henix.squash.framework.annotations.TAResource;
import java.util.Properties;
import java.util.Set;

/**
 * PropertiesResource implementation
 * 
 * @author qtran
 */
@TAResource("properties")
public class PropertiesResource implements Resource<PropertiesResource>{

    private Properties properties;

    public PropertiesResource() {
    }

    public PropertiesResource(Properties properties) {
        this.properties = properties;
    }
    
    @Override
    public PropertiesResource copy() {
        Properties copiedProperties = new Properties();
        Set<String> myEntries = properties.stringPropertyNames();
        for (String myEntry : myEntries) {
            copiedProperties.setProperty(myEntry, properties.getProperty(myEntry));
        }
        return new PropertiesResource(copiedProperties);
    }

    @Override
    public void cleanUp() {
        //
    }

    public Properties getProperties() {
        return properties;
    }
    
}
