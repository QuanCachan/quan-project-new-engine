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
 * ResourceRepositories are the gateways through which you can access to your
 * test data, aka {@link Resource}. ResourceRepositories belongs to the Squash
 * TA engine domain, and not to the System Under Test (SUT).
 * </p>
 *
 * <p>
 * Note that this class must be created by a corresponding factory :
 * {@link RepositoryCreator}. Implementations of ResourceRepository must use the
 * <strong>{@link org.squashtest.ta.framework.annotations.ResourceType}</strong>
 * annotation (not
 * {@link org.squashtest.ta.framework.annotations.EngineComponent})</p>
 *
 *
 * <p>
 * An instance of ResourceRepository may be shared by multiple entities, so be
 * careful if you make it stateful.</p>
 *
 *
 * <p>
 * When requested to look for a resource, the engine will query in turn each
 * ResourceRepository instances loaded in its context. If a repository instance
 * fails normally (typically : resource not found), it must not raise an
 * exception but return null instead; the engine will then proceed to the next
 * repository.
 * </p>
 *
 * <p>
 * For any other reason a repository instance can still raise an exception. The
 * engine will catch it and carry on nonetheless. However, if eventually no
 * repository instance could find and load the resource the test will fail and
 * report all exceptions that were raised during the operation.
 * </p>
 *
 * <p>
 * By design a ResourceRepository is read-only and is meant to load test
 * resources (not runtime-produced resources) into the test context. With the
 * concern of keeping the test data safe from spurrious modifications, the
 * engine will enforce that behaviour by copying the resources loaded from the
 * repositories before including them in the test context.
 * </p>
 *
 * <p>
 * A same class may implement both ResourceRepository and {@link Target}, though
 * this is discouraged. If you do so you only need to supply either a
 * {@link RepositoryCreator} or {@link TargetCreator}.
 * </p>
 *
 * @author qtran
 */
public interface ResourceRepository {

    /**
     * Before the test suite begins a resource repository may need some code to
     * be executed (reset, open connection and whatnot). The engine will invoke
     * that method before any instruction is executed.
     *
     */
    void init();

    /**
     * After each test : the engine will invoke reset() to keep the test
     * environment clean.
     *
     */
    void reset();

    /**
     * When the tests are over a resources repository may need cleaning (close
     * connections and whatnot). The engine will call that method at the end of
     * the lifecycle.
     */
    void cleanup();

    /**
     * @return a Properties object that contains the configuration of this
     * instance at the time {@link #getConfiguration()} was called.
     */
    Properties getConfiguration();

    /**
     * <p>
     * Given it's name, the ResourceRepository should return the resource as a
     * FileResource if found, or null if not found.</p>
     *
     * @param name
     * @return a File or null.
     */
    FileResource findResources(String name);

}
