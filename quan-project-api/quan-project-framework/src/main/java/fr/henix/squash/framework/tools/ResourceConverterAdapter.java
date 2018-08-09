package fr.henix.squash.framework.tools;

import fr.henix.squash.framework.annotations.TAResourceConverter;
import fr.henix.squash.framework.components.ResourceConverter;
import fr.henix.squash.framework.exception.BrokenTestException;

/**
 *
 * @author qtran
 */
public class ResourceConverterAdapter implements ComponentAdapter {

    ResourceConverter<?, ?> converter;

    public ResourceConverterAdapter(final ResourceConverter<?, ?> converter) {
        this.converter = converter;
    }

    @Override
    public String getNature() {
        TAResourceConverter annotation = converter.getClass().getAnnotation(TAResourceConverter.class);
        if (annotation == null) {
            throw new BrokenTestException("ResourceConverter class " + converter.getClass() + " is not annotated @TAResourceConverter and will not work!");
        } else {
            return annotation.value();
        }
    }

}
