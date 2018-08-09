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

import java.net.URL;

/**
 * Pretty much like a {@link RepositoryCreator}, a TargetCreator will instances
 * of targets. Likewise, its job is :
 * <ol>
 * <li>Given a File on a properties file, it must tell whether it can create the
 * target or not, by analysing the content</li>
 * <li>If it accepts the job, it should return a properly configured Target</li>
 * </ol>
 *
 * <p>
 * A unique instance of each implementation will be created at the engine init
 * phase, then it will be discarded.</p>
 *
 * <p>
 * Remember : annotate your classes using @EngineComponent, though the text
 * argument will not be meaningful here.</p>
 *
 * @author qtran
 */
public interface TargetCreator<TARGET extends Target> {

    /**
     * Check if this target creator supports the target type for a given
     * configuration file.
     *
     * @param propertiesFile the target configuration file to check
     * @return
     */
    boolean canInstantiate(URL propertiesFile);

    /**
     * Given a File on a properties file, will set up an instance of a specific
     * Target and return it. Warning : this method may return successfully even
     * if the given Properties does not match the Target, if all (if any) of his
     * required properties are set by pure chance or not.
     *
     * @param propertiesFile the target configuration file
     * @return
     */
    TARGET createTarget(URL propertiesFile);
}
