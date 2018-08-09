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
 * A TestResult provides statistics about the test. When the test failed for
 * some reasons, additional informations will be available in the
 * ExecutionDetails returned by {@link #getFirstFailure()}.
 *
 * @author qtran
 */
public interface TestResult extends ResultPart {

    /**
     * @return the id of the test ( optionally given for ordered test )
     */
    String getTestId();

    /**
     * @return the total number of instructions.
     */
    int getTotalInstructions();

    /**
     * @return details regarding the instruction that failed (if the test
     * failed), or null if all went fine.
     */
    ExecutionDetails getFirstFailure();

    /**
     * @return how many assertions were actually verified.
     */
    int getTotalAssertions();

    /**
     * @return how many commands were actually executed.
     */
    int getTotalCommands();

    /**
     * Return the setup {@link PhaseResult}
     *
     * @return The setup phase result
     */
    PhaseResult getSetupPhaseResult();

    /**
     * Return the test {@link PhaseResult}
     *
     * @return The test phase result
     */
    PhaseResult getTestPhaseResult();

    /**
     * Return the teardown {@link PhaseResult}
     *
     * @return The teardown phase result
     */
    PhaseResult getTeardownPhaseResult();

    /**
     * @return <code>true</code> if whole errors and failures of the testcase
     * come from instructions using the "continue" mode or if there is no error
     * or failure.
     */
    boolean hasOnlyFailOrErrorWithContinue();
}
