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
package fr.henix.squash.framework.test.definition;

/**
 * This class packs the setup and teardown processes used to define a testing
 * environment.
 *
 * @author qtran
 */
public class Environment {

    /**
     * The definition of the setup process for this environment.
     */
    private Test setUp;

    /**
     * The definition of the tearDown process for this environment.
     */
    private Test tearDown;

    /**
     * @return The definition of the setup process for this environment.
     */
    public Test getSetUp() {
        return setUp;
    }

    public void setSetUp(Test setUp) {
        this.setUp = setUp;
    }

    /**
     * @return The definition of the teardown process for this environment.
     */
    public Test getTearDown() {
        return tearDown;
    }

    public void setTearDown(Test tearDown) {
        this.tearDown = tearDown;
    }
}
