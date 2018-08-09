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
package fr.henix.squash.framework.test.result;

/**
 * An ecosystem result will hold information about the execution of an
 * {@link org.squashtest.ta.framework.test.definition.Ecosystem} (tests sharing
 * a common environment).
 *
 * @author qtran
 */
public interface EcosystemResult extends CompositeResultPart<TestResult> {

    /**
     * Result of the setup process, in case you need to drill down.
     *
     * @return the execution report of the setup script.
     */
    TestResult getSetupResult();

    /**
     * Result of the tearDown process, in case you need to drill down.
     *
     * @return the execution report of the tearDown script.
     */
    TestResult getTearDownResult();
}
