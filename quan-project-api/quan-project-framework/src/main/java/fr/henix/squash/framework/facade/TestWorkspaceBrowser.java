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
package fr.henix.squash.framework.facade;

import fr.henix.squash.framework.components.ResourceRepository;
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * <p>
 * A TestWorkspaceBrowser allows the engine to find user test workspace
 * configuration files, mainly the repositories and targets. This interface is
 * handy in case the test workspace is not exactly local, implementations may
 * then act as a proxy.</p>
 *
 * <p>
 * The TestWorkspaceBrowser must also supply a default
 * {@link ResourceRepository} </p>
 *
 * @author qtran
 */
public interface TestWorkspaceBrowser {

    /**
     * Must a list of the configuration files defining the targets.
     *
     * @return an iterator on the said files.
     */
    List<URL> getTargetsDefinitions();

    /**
     * Must supply a list of the configuration files defining the repositories.
     *
     * @return a list of the said files. This list does not include an entry for
     * the default repository, as it is default defined by the workspace
     * browser.
     */
    List<URL> getRepositoriesDefinitions();

    /**
     * The test workspace also contains resources that one may want to access
     * and the TestWorkspaceBrowser must supply a repository on them. As a
     * consequence the test workspace does not have to define a config file for
     * a ResourceRepository that will point to itself.
     *
     * @return
     */
    ResourceRepository getDefaultResourceRepository();

    /**
     * The test workspace also contains resources that one may want to access
     * and the TestWorkspaceBrowser must supply a repository on them. As a
     * consequence the test workspace does not have to define a config file for
     * a ResourceRepository that will point to itself.
     *
     * @return
     */
    File getDefaultResourceFile();
}
