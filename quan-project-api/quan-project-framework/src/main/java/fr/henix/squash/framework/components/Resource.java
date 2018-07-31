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
package fr.henix.squash.framework.components;

/**
 * <p>
 * A Resource represents raw data, refined data, configuration or else that will
 * be used as input for engine components, and are produced by
 * {@link ResourceRepository}, {@link ResourceConverter} or {@link Command}
 * objects. </p>
 *
 * <p>
 * Implementations must be annotated @ResourceType, which will map this class of
 * resource to a user friendly string identifier.</p>
 *
 * <p>
 * A new instance of this class will be created every time it is needed, so you
 * may safely make it stateful.</p>
 *
 * @author qtran
 * @param <R>
 */
public interface Resource<R extends Resource<R>> {

    /**
     * Data used during tests should never be direct hangs on user-defined
     * datasets. A Resource should be able to deliver deep copies of itself.
     *
     * @return
     */
    R copy();

    /**
     * a Resource may have to clean physical resources it has a hold on. This
     * method will ask the Resource to clean its physical counterparts.
     */
    void cleanUp();
}
