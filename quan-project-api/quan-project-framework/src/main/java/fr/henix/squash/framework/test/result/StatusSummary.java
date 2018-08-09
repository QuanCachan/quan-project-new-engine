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
 * This report bean is a test status summary. If the test failed, it contains
 * the failed instruction's {@link ExecutionDetails} and contextual information
 * such as the {@link Phase} the failed instruction was part of.
 *
 * @author qtran
 */
public interface StatusSummary {

    /**
     * @return phase during which the first failure occurred.
     */
    Phase getFailurePhase();

    /**
     * Report if the execution failed.
     *
     * @return <code>true</code> if execution failed, <code>false</code>
     * otherwise.
     */
    boolean isFailed();

    /**
     * @return the execution details of the first failed instruction, if the
     * test failed.
     */
    ExecutionDetails getFailureDetails();

    /**
     * @return the local context identifier as a tag of the scope where the
     * failure occurred, for debugging purpose. May return <code>null</code> if
     * no local context was initialized at the time of the failure.
     */
    Integer getFailureContextIdentifier();
}
