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
import fr.henix.squash.framework.components.UnaryAssertion;



/**
 *
 * @author qtran
 */
public class UnaryAssertionDefinition extends EngineComponentDefinition<UnaryAssertion<?>> {
    
    public static final Nature UNARY_ASSERTION_NATURE = new Nature("(unary assertion)");
    
    public UnaryAssertionDefinition(Nature firstNature, Nature category, Class<UnaryAssertion<?>> componentClass) {
        super(firstNature, UNARY_ASSERTION_NATURE, category, componentClass);
    }
    
}
