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
 *
 * @author qtran
 */
public abstract class AbstractTestInstruction implements TestInstruction{

    /**
     * OPTIONAL : May be used to store useful informations like a human readable
     * representation of the instruction, comments etc.
     */
    protected String text;

    /**
     * The line number of the original text instruction which leads to this
     * instruction
     */
    private String lineNumber = "0";

    /**
     * The type of the instruction
     */
    private InstructionType type;

    /**
     * Flag which indicates if the next instruction should be execute when the
     * instruction finish in failure or in error.
     */
    private boolean executeNextInstructionOnFailOrError = false;

    @Override
    public String toText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getLine() {
        return lineNumber;
    }

    @Override
    public void setLine(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public InstructionType getType() {
        return type;
    }

    void setType(InstructionType type) {
        this.type = type;
    }

    @Override
    public boolean continueOnFailOrError() {
        return executeNextInstructionOnFailOrError;
    }

    void setContinueOnFailOrError(boolean continueOn) {
        executeNextInstructionOnFailOrError = continueOn;
    }
}
