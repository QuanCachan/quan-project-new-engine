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
package fr.henix.squash.framework.test.instructions;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class which represents the meta instructions. Each meta instruction
 * should extend this class.
 *
 * @author qtran
 */
public abstract class MetaInstruction extends AbstractTestInstruction {

    /**
     * List of instructions
     */
    private List<TestInstruction> instructions = new LinkedList<TestInstruction>();

    /**
     * This method return the list of instructions contains in the meta
     * instruction
     *
     * @return The list of instruction
     */
    public List<TestInstruction> getInstructions() {
        return instructions;
    }

    /**
     * Add an instruction to the list of instructions contain in the meta
     * instruction
     *
     * @param instruction An instruction to add
     */
    public void addInstruction(TestInstruction instruction) {
        instructions.add(instruction);
    }

    /**
     * Add a list of instructions to the list of instructions contain in the
     * meta instruction
     *
     * @param instructionsToAdd The list of instructions to add
     */
    public void addAllInstructions(List<TestInstruction> instructionsToAdd) {
        instructions.addAll(instructionsToAdd);
    }
}
