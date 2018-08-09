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
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * A DefineResourceInstruction allows the definition of a resource directly in
 * the test context, thus that need not to be loaded from a repository nor
 * resulting from a {@link org.squashtest.ta.framework.components.Command} or
 * {@link org.squashtest.ta.framework.components.ResourceConverter} job. Those
 * are called 'script-defined instruction'.</p>
 *
 * <p>
 * A script-defined resource will be stored in the test context as a
 * {@link org.squashtest.ta.framework.components.FileResource}, which in this
 * case will be a text file. Including binary data in a define instruction is
 * discouraged because the data will be scrambled by the decoding.</p>
 *
 * @author qtran
 */
public class DefineResourceInstruction extends AbstractTestInstruction {

    /**
     * OPTIONAL : The content of the file, as a list of String. Though optional,
     * leaving it empty would make no sense.
     */
    private List<String> resourceContent = new LinkedList<String>();

    /**
     * MANDATORY : The name of the script-defined resource, under which it will
     * be stored in the test context.
     */
    private ResourceName name;

    /**
     * OPTIONAL : The scope of the newly defined resource.
     */
    private ResourceName.Scope scope = ResourceName.Scope.SCOPE_TEST;

    /**
     * Default constructor. Set instruction type to
     * {@link InstructionType#DEFINE}
     */
    public DefineResourceInstruction() {
        setType(InstructionType.DEFINE);
    }

    public List<String> getResourceContent() {
        return resourceContent;
    }

    public void addLine(String line) {
        resourceContent.add(line);
    }

    public void setResourceName(ResourceName name) {
        this.name = name;
    }

    public ResourceName getResourceName() {
        return name;
    }

    public ResourceName.Scope getScope() {
        return scope;
    }

    public void setScope(ResourceName.Scope scope) {
        this.scope = scope;
    }

    @Override
    public void visit(TestInstructionVisitor visitor) {
        visitor.accept(this);
    }
}
