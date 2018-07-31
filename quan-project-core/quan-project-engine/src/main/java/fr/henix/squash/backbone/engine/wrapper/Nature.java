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

/**
 * <p>A Nature is an explicit metadata regarding classes. It could have been the raw {@link Class} of a Resource
 * or other engine components but the Nature gives here just what we need for a convenient use.
 * It will furthermore hold user-friendly informations.</p>
 * 
 * <p>That class exists for infrastructure purpose. Namely, they are meant to be keys in Maps,
 * so each instance must be unique (ie, no multiple instances of nature having the same name are allowed.</p>
 * 
 * @author qtran
 */
public class Nature {
    
    public static final Nature FILE_RESOURCE_NATURE = new Nature("file");
    
    private String name;

    public Nature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}
