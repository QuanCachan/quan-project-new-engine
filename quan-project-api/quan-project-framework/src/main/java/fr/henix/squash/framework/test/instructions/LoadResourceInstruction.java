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
 * A LoadResourceInstruction will instruct the
 * {@link org.squashtest.ta.framework.components.ResourceRepository}/ies in
 * order to load resources and bring them into the test context. The engine will
 * look for that resources through one or several repositories (depending on
 * which are available and/or the instruction arguments) and will store the
 * resource in the test context as a
 * {@link org.squashtest.ta.framework.components.FileResource}.
 *
 * @author qtran
 */
public class LoadResourceInstruction extends AbstractTestInstruction {

    /**
     * MANDATORY : the name of the resource we want to load. If no
     * {@link #newName} is supplied, the resource will keep its name when stored
     * in the test context. If no {@link #repositoryName} is supplied, each
     * available repository will be searched in turn.
     */
    private ResourceName resourceName;

    /**
     * OPTIONAL : specifies from which repository we want to search for the
     * resource. This argument may be useful to speed up the the process, as the
     * search will be restricted to that repository. It also may helps with
     * ambiguous resource name (ie, the same name is available in two or more
     * repositories).
     */
    private String repositoryName;

    /**
     * OPTIONAL : Once the resource is loaded, if a new name is supplied t he
     * resource will be renamed accordingly and be registered under that name in
     * the test context.
     *
     */
    private ResourceName newName;

    /**
     * Default constructor. Set instruction type to {@link InstructionType#LOAD}
     */
    public LoadResourceInstruction() {
        setType(InstructionType.LOAD);
    }

    @Override
    public void visit(TestInstructionVisitor visitor) {
        visitor.accept(this);
    }

    public ResourceName getResourceName() {
        return resourceName;
    }

    public void setResourceName(ResourceName resourceName) {
        this.resourceName = resourceName;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public ResourceName getNewName() {
        return newName;
    }

    public void setNewName(ResourceName alias) {
        this.newName = alias;
    }
}
