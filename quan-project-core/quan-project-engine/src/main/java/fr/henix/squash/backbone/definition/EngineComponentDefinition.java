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

package fr.henix.squash.backbone.definition;

import fr.henix.squash.backbone.engine.wrapper.Nature;

/**
 * That class is a simple ID card of an engine component. 
 * 
 * Names like {@link #firstNature} and {@link #secondNature} may be unappropriate at first glance, 
 * since their meanings depends on the class they are associated to.
 * 
 * See {@link org.squashtest.ta.backbone.engine.wrapper.ResourceConverterHandler},
 * {@link org.squashtest.ta.backbone.engine.wrapper.BinaryAssertionHandler}
 * or {@link org.squashtest.ta.backbone.engine.wrapper.CommandHandler} to get a glimpse on how it works.
 * 
 * @author qtran
 * @param <COMPONENT>
 */
public class EngineComponentDefinition<COMPONENT> {
    private final Nature firstNature;
    private final Nature secondNature;
    private final Nature category;
    private final Class<COMPONENT> componentClass;

    public EngineComponentDefinition(Nature firstNature, Nature secondNature, Nature category, Class<COMPONENT> componentClass) {
        this.firstNature = firstNature;
        this.secondNature = secondNature;
        this.category = category;
        this.componentClass = componentClass;
    }

    public Nature getFirstNature() {
        return firstNature;
    }

    public Nature getSecondNature() {
        return secondNature;
    }

    public Nature getCategory() {
        return category;
    }

    public Class<COMPONENT> getComponentClass() {
        return componentClass;
    }

}
