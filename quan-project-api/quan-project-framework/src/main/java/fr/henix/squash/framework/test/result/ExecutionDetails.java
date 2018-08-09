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

import java.util.Collection;
import java.util.List;

/**
 * An ExecutionDetails is mainly relevant when a given instruction fails. If so
 * an ExecutionDetails will shipped along a failed
 * {@link org.squashtest.ta.framework.test.definition.TestSuite}.
 *
 * @author qtran
 */
public interface ExecutionDetails {

    /**
     * @return The status of execution of the instruction
     */
    GeneralStatus getStatus();

    /**
     * @return the type of instruction {@link InstructionType}.
     */
    InstructionType instructionType();

    /**
     * Position of the instruction in the current phase
     *
     * @return the index of the instruction within its own phase + 1;
     */
    int instructionNumberInPhase();

    /**
     * Absolute position of the instruction in test
     *
     * @return the absolute position of the instruction
     */
    int instructionNumber();

    /**
     * @return the textual representation of the Instruction (see
     * {@link TestInstruction})
     */
    String instructionAsText();

    /**
     *
     * @return the exception that triggered the instruction failure, or null if
     * everything went fine.
     */
    Exception caughtException();

    /**
     * When the GlobalStatus is ERROR or FAILURE, the resources used by the
     * instruction will be supplied here, along with some metadata. When the
     * GlobalStatus is SUCCESS or NOT_RUN, the list is empty.
     *
     * @return
     */
    Collection<ResourceAndContext> getResourcesAndContext();

    void addResourceAndContext(ResourceAndContext contextResource);

    List<ExecutionDetails> getChildrens();

    List<ExecutionDetails> getErrorOrFailedChildrens();

    int getErrorOrFailedWithContinue();
}
