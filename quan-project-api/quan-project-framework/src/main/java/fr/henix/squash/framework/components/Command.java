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

import java.util.Collection;

/**
 * <p>
 * A Command will perform an operation on a {@link Target} by applying a
 * {@link Resource} on it in a special way. The result, if any, is returned as a
 * {@link Resource}.
 * </p>
 *
 * <p>
 * A new instance of this class will be created every time it is needed, so you
 * may safely make it stateful.</p>
 *
 * @author qtran
 * @param <RESOURCE>
 * @param <TARGET>
 */
public interface Command<RESOURCE extends Resource<RESOURCE>, TARGET extends Resource<TARGET>> {

    /**
     * Similar to {@link ResourceConverter#addConfiguration(Collection)}
     *
     * <p>
     * If this methods detects bad configuration, it should throw
     * {@link org.squashtest.ta.framework.exception.BadDataException} (for
     * blocking errors only, like missing data. As of version 1.0.0, useless or
     * unknown configuration resources can be ignored, albeit with warning, of
     * other data define a usable configuration)
     * </p>
     *
     * @param configuration
     */
    void addConfiguration(Collection<Resource<?>> configuration);

    /**
     * set the target
     *
     * @param target
     */
    void setTarget(TARGET target);

    /**
     * set the resource
     *
     * @param resource
     */
    void setResource(RESOURCE resource);

    /**
     * <p>
     * If some anomaly in the INPUT resource is detected, this method should
     * throw {@link org.squashtest.ta.framework.exception.BadDataException}
     * </p>
     *
     * <p>
     * The actual return type MUST BE a concrete implementation of
     * {@link Resource}, you cannot leave it as {@literal Resource<?>}, even
     * when you plan to return null. If you don't specify an appropriate type
     * the engine will fail early and complain that
     * 'org.squashtest.ta.framework.components.Resource' is lacking its
     * annotation, which is legit : that interface is not supposed to support
     * the said annotation.</p>
     *
     * <p>
     * If a converter cannot work because it doesn't have the proper
     * configuration (see {@link #addConfiguration(Collection)}, it must throw
     * an
     * {@link org.squashtest.ta.framework.exception.IllegalConfigurationException}.
     * </p>
     * <p>
     * For any other error, please throw
     * {@link org.squashtest.ta.framework.exception.InstructionRuntimeException}
     * (meaning the error is not due to data, but to some other , probably
     * environment related, problem)
     * </p>
     *
     * @return a Resource of your choice - empty or not, it must be so.
     */
    Resource<?> apply();
    
    /**
     * Once it has been run, a command doesn't need configuration anymore. Discard it.
     */
    void cleanUp();
}
