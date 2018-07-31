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

import fr.henix.squash.framework.exception.AssertionFailedException;
import java.util.Collection;

/**
 * <p>
 * An unary assertion accepts one resource and test it. If the test passes it
 * returns nothing, otherwise it *must* throw a
 * {@link AssertionFailedException}. A AssertionFailedException means a test
 * failure, while any other exception means a test error.</p>
 *
 * <p>
 * Remember : a UnaryAssertion must be annotated using @EngineComponent. In this
 * context the value of the annotation is the name of the category of the
 * assertion ("empty", "odd.number" etc), that will classify the assertions
 * regardless of the concrete implementation of Resource they use.</p>
 *
 *
 * <p>
 * A new instance of this class will be created every time it is needed, so you
 * may safely make it stateful.</p>
 *
 * @author qtran
 * @param <ACTUAL>
 */
public interface UnaryAssertion<ACTUAL extends Resource<ACTUAL>> {

    void setActualResult(ACTUAL actual);

    /**
     * The configuration is a collection of {@link Resource} that contains more
     * data, usually to configure the behavior of the assertion (hence the
     * name).
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
     * will perform the test against the supplied inputs. If success nothing
     * must be returned, if failed it must throw a
     * {@link AssertionFailedException}.
     *
     * <p>
     * If some TECHNICAL anomaly in the INPUT resource is detected, this method
     * should throw
     * {@link org.squashtest.ta.framework.exception.BadDataException}
     * </p>
     * <p>
     * If an assertion cannot work because it doesn't have the proper
     * configuration (see {@link #addConfiguration(Collection)}, it must throw
     * an
     * {@link org.squashtest.ta.framework.exception.IllegalConfigurationException}.
     * </p>
     * <p>
     * Other exceptions can be directly thrown if they are uncheked, wrapped in
     * {@link org.squashtest.ta.framework.exception.InstructionRuntimeException}
     * if checked. For any other error, please throw
     * {@link org.squashtest.ta.framework.exception.InstructionRuntimeException}
     * (meaning the error is not due to data, but to some other , probably
     * environment related, problem)
     * </p>
     *
     * @throws AssertionFailedException if the asserted condition is false.
     */
    void test() throws AssertionFailedException;
}
