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
import java.util.Collection;
import java.util.LinkedList;

/**
 * An ExecuteCommandInstruction simply holds informations to run a
 * {@link org.squashtest.ta.framework.components.Command}. A command will - and
 * must - return a resource
 *
 * It needs :
 *
 * <ul>
 * <li>the name of the resource that the command will use against the
 * target,</li>
 * <li>the name of the target,</li>
 * <li>the category of the command,</li>
 * <li>the name of the result of the command.</li>
 * </ul>
 *
 * Additionally the command may take additional configuration. This
 * configuration is given as a list of names, each names referring to a
 * FileResource in the context.
 *
 * @author qtran
 */
public class ExecuteCommandInstruction extends AbstractTestInstruction {

    /**
	 * MANDATORY : the name of the resource used by the command
	 */
	private ResourceName resourceName;
	
	/**
	 * MANDATORY : the name of the target the command will act upon
	 */
	private String targetName;
	
	/**
	 * MANDATORY : the category of the command. Refer to {@link org.squashtest.ta.framework.components.Command} 
	 * and {@link org.squashtest.ta.framework.annotations.EngineComponent} 
	 * for details on what a category is in that context.
	 */
	private String commandCategory;
	
	/**
	 * OPTIONAL : a non null but possibly empty collection of resource names.  
	 */
	private Collection<ResourceName> commandConfiguration = new LinkedList<ResourceName>();
	
	/**
	 * MANDATORY : the name of the resulting resource, and the given resource will be stored in the test context under that name.
	 */
	private ResourceName resultName;
	
	/**
	 * Default constructor. Set instruction type to {@link InstructionType#COMMAND}
	 */
	public ExecuteCommandInstruction(){
		setType(InstructionType.COMMAND);
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


	public String getTargetName() {
		return targetName;
	}


	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}


	public String getCommandCategory() {
		return commandCategory;
	}


	public void setCommandCategory(String commandName) {
		this.commandCategory = commandName;
	}

	public Collection<ResourceName> getCommandConfiguration() {
		return commandConfiguration;
	}

	public void addCommandConfiguration(ResourceName configuration){
		commandConfiguration.add(configuration);
	}

	public void addCommandConfiguration(Collection<ResourceName> commandConfiguration) {
		this.commandConfiguration.addAll(commandConfiguration);
	}
	
	public void setResultName(ResourceName name){
		this.resultName = name;
	}
	
	public ResourceName getResultName(){
		return resultName;
	}

}
