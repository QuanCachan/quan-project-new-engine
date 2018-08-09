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

import java.util.Properties;

/**
 * <p>
 * A Target represents the system under test (SUT). It is something on which a
 * command will be applied.</p>
 *
 * <p>
 * Note that this class must be created by a corresponding factory :
 * {@link TargetCreator}. Implementations of Target must use the
 * <strong>{@link org.squashtest.ta.framework.annotations.ResourceType}</strong>
 * annotation (not
 * {@link org.squashtest.ta.framework.annotations.EngineComponent})</p>
 *
 * <p>
 * An instance of Target may be shared by multiple entities, so be careful if
 * you make it stateful.</p>
 *
 * <p>
 * An implementation of Target is not limited to the following methods. Those
 * methods simply ensure that the engine will be able to manage it as a Target.
 * Implementations are thus invited to expose any method that will help other
 * implementors to use it in their own {@link Command} objects.</p>
 *
 * <p>
 * The implementor should take care of possible multithread issues.</p>
 *
 * @author qtran
 */
public interface Target {

    /**
     * Before the test suite begins : a target may need some code to be executed
     * (reset, open connection and whatnot).
     *
     * @return true if the initialisation was ok and (if possible) if the target
     * does exist, false otherwise
     */
    boolean init();

    /**
     * After each test : the engine will invoke reset() to keep the test
     * environment clean.
     *
     */
    void reset();

    /**
     * When the tests suite ends : a target may need cleaning (close connections
     * and whatnot). The cleanup will be invoked after the whole test suite has
     * been completed.
     */
    void cleanup();

    /**
     * @return a Properties object that contains the configuration of this
     * instance at the time {@link #getConfiguration()} was called.
     */
    Properties getConfiguration();
}
