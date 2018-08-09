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
 * A RepositoryCreator is meant to configure instances of specific
 * implementations of ResourceRepository. It goal is twofold :
 * <ol>
 * <li>Given a File on a properties file, it must tell whether it can create the
 * resource or not, by analyzing the content</li>
 * <li>If it accepts the job, it should return a properly configured
 * ResourceRepository</li>
 * </ol>
 *
 * In that later sense we have here some weak factory.
 *
 * <p>
 * A unique instance of each implementation will be created at the engine init
 * phase, then it will be discarded.</p>
 *
 * <p>
 * Remember : annotate your classes using @EngineComponent, though the string
 * argument will not be meaningful here.</p>
 *
 * @author qtran
 */
public interface RepositoryCreator<REPOSITORY extends ResourceRepository> {

    /**
     * Test if that repository creator handles a given repository definition.
     *
     * @param propertiesURL URL for the resource repository definition to
     * assess.
     * @return <code>true</code> if this repository creator can handle the
     * definition URL, <code>false</code> if not.
     */
    boolean canInstantiate(URL propertiesURL);

    /**
     * Given a File on a properties file, will set up an instance of a specific
     * ResourceRepository and return it. Warning : this method may return
     * successfully even if the given Properties does not match the
     * ResourceRepository, if all (if any) of his required properties are set by
     * pure chance or not.
     *
     * @param propertiesURL the URL of the repository definition file.
     * @return a repository instance.
     * @throws org.squashtest.ta.framework.exception.BrokenTestException if the
     * @param propertiesFile URL is not valid for this RepositoryCreator. The
     * transmitted URL is supposed to have been tested with
     * {@link #canInstantiate(URL)}
     */
    REPOSITORY createRepository(URL propertiesURL);
}
