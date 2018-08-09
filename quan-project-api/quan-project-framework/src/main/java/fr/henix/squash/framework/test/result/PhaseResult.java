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

import java.util.List;

/**
 * Manage the result of a {@link Phase} of a testcase
 *
 * @author qtran
 */
public interface PhaseResult extends ResultPart {

    /**
     * @return All the instructions of the phase
     */
    List<ExecutionDetails> getAllInstructions();

    /**
     * @return The instructions which failed or raised an error during the phase
     * execution
     */
    List<ExecutionDetails> getFailedInstructions();

    /**
     * @return The number of instructions with "continue" mode which failed /
     * raised an error during the phase execution
     */
    int getFailedOrErrorWithContinue();

    /**
     * @return The {@link Phase} corresponding to this {@link PhaseResult}
     */
    Phase getPhase();

    @Override
    GeneralStatus getStatus();

    /**
     * This method set a new status to the phase
     *
     * @param phaseStatus The new Status
     */
    void setPhaseStatus(GeneralStatus phaseStatus);

    /**
     * @return The number of executed {@link InstructionType#COMMAND} during the
     * phase
     */
    int getTotalCommands();

    /**
     * @return The number of executed {@link InstructionType#ASSERTION} during
     * the phase
     */
    int getTotalAssertions();

    /**
     * @return The number of executed instructions during the phase
     */
    int getTotalInstructions();
}
