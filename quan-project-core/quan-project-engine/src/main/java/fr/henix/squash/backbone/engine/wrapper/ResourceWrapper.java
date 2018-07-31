/**
 *     This file is part of the Squashtest platform.
 *     Copyright (C) 2018 - 2018 HENIX
 *
 *     See the NOTICE file distributed with this work for additional
 *     information regarding copyright ownership.
 *
 *     This is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     this software is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this software.  If not, see <http://www.gnu.org/licenses />.
 */
package fr.henix.squash.backbone.engine.wrapper;

import fr.henix.squash.framework.components.Resource;
import fr.henix.squash.framework.test.instructions.ResourceName;
import fr.henix.squash.framework.test.result.ResourceGenerator;

/**
 * A ResourceWrapper wraps an instance of Resource in its context metadata.
 * These metadata helps the engine to manage its components and resources, and
 * makes them easily accessible from the outer context (namely the
 * {@link org.squashtest.ta.framework.test.instructions.TestInstruction}s that
 * call theses items).
 *
 * @author qtran
 */
public class ResourceWrapper {

    private Nature nature;

    private ResourceName name;

    private Resource<?> wrappedResource;

    private ResourceGenerator generator;

    public ResourceWrapper() {
        super();
    }

    public ResourceWrapper(Nature nature, ResourceName name, Resource<?> wrappedResource, ResourceGenerator generator) {
        super();
        this.nature = nature;
        this.name = name;
        this.wrappedResource = wrappedResource;
        this.generator = generator;
    }

    public Nature getNature() {
        return nature;
    }

    public ResourceName getName() {
        return name;
    }

    public Resource<?> unwrap() {
        return wrappedResource;
    }

    public ResourceGenerator getGenerator() {
        return generator;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public void setName(ResourceName name) {
        this.name = name;
    }

    public void wrap(Resource<?> wrappedResource) {
        this.wrappedResource = wrappedResource;
    }

    public void setGenerator(ResourceGenerator generator) {
        this.generator = generator;
    }

    public ResourceWrapper copy() {
        Resource<?> copy = wrappedResource.copy();
        return new ResourceWrapper(nature, name, copy, generator);
    }

    /**
     * @see Resource#cleanUp()
     */
    public void cleanUp() {
        wrappedResource.cleanUp();
    }
}
