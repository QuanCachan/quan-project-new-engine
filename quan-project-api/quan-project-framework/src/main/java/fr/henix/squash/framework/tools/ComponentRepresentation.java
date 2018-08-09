package fr.henix.squash.framework.tools;

import fr.henix.squash.framework.components.BinaryAssertion;
import fr.henix.squash.framework.components.Resource;
import fr.henix.squash.framework.components.ResourceConverter;
import fr.henix.squash.framework.components.UnaryAssertion;

/**
 * Reporting helper to help output TA component references in messages.
 *
 * @author qtran
 */
public class ComponentRepresentation {

    private ComponentAdapter componentAdapter;

    public String toString() {
        return componentAdapter.getNature();
    }

    public ComponentRepresentation(final Resource<?> res) {
        this.componentAdapter = new ResourceAdapter(res);
    }

    public ComponentRepresentation(final BinaryAssertion<?, ?> asser) {
        this.componentAdapter = new BinaryAssertionAdapter(asser);
    }

    public ComponentRepresentation(final UnaryAssertion<?> asser) {
        this.componentAdapter = new UnaryAssertionAdapter(asser);
    }

    public ComponentRepresentation(final ResourceConverter<?, ?> convert) {
        this.componentAdapter = new ResourceConverterAdapter(convert);
    }
}
