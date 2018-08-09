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
 * Enumeration for instruction type<br>
 * Note : RESET_LOCAL_CONTEXT is for internal purpose only, no matching DSL
 * keyword exists for TA scripting.
 *
 * @author qtran
 */
public enum InstructionType {
    LOAD, CONVERT, COMMAND, ASSERTION, DEFINE, RESET_LOCAL_CONTEXT, INLINE_META, MACRO, COMMENT;

    /**
     * @return <code>true</code> if the instruction is an internal instruction.
     * Currently it returns true when it's a
     * {@link InstructionType#RESET_LOCAL_CONTEXT} instruction
     */
    public boolean isInternalInstruction() {
        return this.equals(RESET_LOCAL_CONTEXT);
    }

    /**
     * This method returns true when the instruction is a
     * {@link org.squashtest.ta.framework.test.instructions.UnaryAssertionInstruction}
     * or a
     * {@link org.squashtest.ta.framework.test.instructions.BinaryAssertionInstruction}
     * in assert or in verify mode.
     *
     * @return <code>true</code> if the instruction is an
     * {@link InstructionType#ASSERTION}.
     */
    public boolean isAssertion() {
        return this.equals(ASSERTION);
    }

    /**
     * @return <code>true</code> if the instruction is an
     * {@link InstructionType#COMMAND}.
     */
    public boolean isCommand() {
        return this.equals(COMMAND);
    }

    /**
     * This method return <code>true</code> when the instruction is a meta
     * instruction. Currently return if type is
     * {@link InstructionType#INLINE_META} or {@link InstructionType#MACRO}
     *
     * @return <code>true</code> if the instruction is of type meta instruction
     */
    public boolean isMeta() {
        return this.equals(INLINE_META) || this.equals(MACRO);
    }

    /**
     * This method return <code>true</code> when the instruction is a
     * commentary.
     *
     * @return <code>true</code> if the instruction is of type commentary
     */
    public boolean isComment() {
        return this.equals(COMMENT);
    }

}
