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

import fr.henix.squash.framework.test.event.StatusUpdateListener;
import fr.henix.squash.framework.test.result.SuiteResult;
import fr.henix.squash.framework.test.definition.TestSuite;

/**
 * The Squash TA Engine will implement this interface, user code doesn't need to
 * know how.
 *
 * @author qtran
 */
public interface Engine {

    /**
     * Method to execute a test suite.
     *
     * @param suite the Squash TA test suite to execute.
     * @param resourceBrowser an object to fetch test configuration files for
     * the engine (resource repositories definitions and targets)
     * @return
     */
    SuiteResult execute(TestSuite suite, TestWorkspaceBrowser resourceBrowser);

    /**
     * Register a new status event listener.
     *
     * @param listener the listener to add.
     */
    void addEventListener(StatusUpdateListener listener);

    /**
     * Unregister a status event listener.
     *
     * @param listener the listener to remove.
     * @return <code>true</code> if the listener was known. <code>false</code>
     * if the listener was not known.
     */
    boolean removeEventListener(StatusUpdateListener listener);
}
