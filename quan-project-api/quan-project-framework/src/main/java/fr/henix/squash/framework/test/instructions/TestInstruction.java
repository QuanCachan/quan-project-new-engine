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

import fr.henix.squash.framework.test.result.InstructionType;

/**
 * <p>
 * Conceptually an instruction is an atomic operation that will use available
 * resources, targets etc and make something with them. Basically here, an
 * instruction just describes who are the said resources, targets etc but makes
 * nothing : it merely carry String-based informations to the engine, and the
 * engine will run it by fetching getting the objects corresponding to those
 * informations.</p>
 *
 * <p>
 * The set of instructions is finite and they are all published in this same
 * package, please refer to their definition for details about their purpose.
 * Note that some arguments are mandatory while some other are not :</p>
 * <ul>
 * <li>the mandatory arguments are javadoced MANDATORY followed by a short
 * explanation of what argument is. If the argument is missing the engine will
 * throw an exception and will terminate the test with the status (the rest of
 * the test suite will be executed normaly). </li>
 * <li>the optional arguments are javadoced OPTIONAL followed by a short
 * explanation of what that argument is. These arguments may be left to
 * null.</li>
 * </ul>
 *
 * <p>
 * It is mandatory to define the {@link InstructionType} in the constructor of
 * the instruction</p>
 *
 * <p>
 * Remember that an Instruction, while being a java object, should be attached a
 * textual representation for reporting purpose (see {@link #toText()})</p>
 *
 * @author qtran
 */
public interface TestInstruction {

    /**
     * Gets a textual representation of the instruction. May be used as a
     * commentary for instance, and has no impact on the job itself.
     *
     * @return
     */
    String toText();

    /**
     * Sets the textual representation.
     *
     * @param text
     */
    void setText(String text);

    /**
     * Get the line number of the original text instruction in the TA script
     * which leads to this {@link TestInstruction}. Dot notation is used for
     * instruction inside meta instruction.
     *
     * @return Return the instruction line number
     */
    String getLine();

    /**
     * Set the line number of the text instruction in the original TA script
     *
     * @param lineNumber The instruction line number
     */
    void setLine(String lineNumber);

    /**
     * This method give the type of the current instruction
     *
     * @return The type of the instruction
     */
    InstructionType getType();

    /**
     * Return true if the next instruction should be executed when this
     * instruction finish in error or in failure, else it return false. By
     * default the execution should stop on fail or error. But with a "VERIFY"
     * assertion, this method should return true.
     *
     * @return The flag value
     */
    boolean continueOnFailOrError();

    /**
     * Visitor hook.
     *
     * @param visitor
     */
    void visit(TestInstructionVisitor visitor);
}
