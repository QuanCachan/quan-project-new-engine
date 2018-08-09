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
 * This instruction is used to create a local context for anonymous resources
 * (These resources are created with a generated name in the local context, and
 * cannot conflict with named resources defined by the test.) It also serves as
 * a boundary to group instructions produced by the translation of a test suite
 * factory test atom (ex: the translation of a DSL line).
 *
 * @author qtran
 */
public class ResetLocalContextInstruction extends AbstractTestInstruction {

    private int contextIdentifier;

    /**
     * Default constructor. Set instruction type to
     * {@link InstructionType#RESET_LOCAL_CONTEXT}
     */
    public ResetLocalContextInstruction() {
        setType(InstructionType.RESET_LOCAL_CONTEXT);
    }

    public int getContextIdentifier() {
        return contextIdentifier;
    }

    public void setContextIdentifier(int contextIdentifier) {
        this.contextIdentifier = contextIdentifier;
    }

    @Override
    public String toText() {
        return text == null ? "Anonymous local context." : text;
    }

    @Override
    public void visit(TestInstructionVisitor visitor) {
        visitor.accept(this);
    }
}
