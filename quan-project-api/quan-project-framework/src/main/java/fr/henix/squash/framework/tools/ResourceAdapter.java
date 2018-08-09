package fr.henix.squash.framework.tools;

import fr.henix.squash.framework.annotations.TAResource;
import fr.henix.squash.framework.components.Resource;
import fr.henix.squash.framework.exception.BrokenTestException;

/**
 *
 * @author qtran
 */
public class ResourceAdapter implements ComponentAdapter {

    final Resource<?> res;

    public ResourceAdapter(Resource<?> res) {
        this.res = res;
    }

    @Override
    public String getNature() {
        TAResource annotation = res.getClass().getAnnotation(TAResource.class);
        if (annotation == null) {
            throw new BrokenTestException("Resource class " + res.getClass() + " is not annotated @TAResource and will not work!");
        } else {
            return annotation.value();
        }
    }

}
