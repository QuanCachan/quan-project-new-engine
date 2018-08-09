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
 * The ConvertResourceInstruction simply holds informations to run a
 * ResourceConverter. Those informations are :
 *
 * <ul>
 * <li>resourceName : the name of the resource that must be converted.</li>
 * <li>resultType : the resource type we want to convert the input resource to
 * (see @ResourceType for more details)</li>
 * <li>resultName : the name of the newly created resource.</li>
 * <li>converterCategory : (optional) you may specify the category of the
 * converter (in case the result type cannot disambiguate, see
 * {@link org.squashtest.ta.framework.components.ResourceConverter} for details
 * regarding that issue).
 * </li>
 * </ul>
 *
 * Additionally the converter may take additional configuration. This
 * configuration is given as a list of names, each names referring to a
 * FileResource in the context.
 *
 * @author qtran
 */
public final class ConvertResourceInstruction extends AbstractTestInstruction {

    /**
     * MANDATORY : The name of the resource we want to convert.
     */
    private ResourceName resourceName;

    /**
     * MANDATORY : the name of the resource type we want to convert the resource
     * to. See {@link org.squashtest.ta.framework.annotations.ResourceType} and
     * how its use in implementations of
     * {@link org.squashtest.ta.framework.components.Resource}. Here we do not
     * explicitly call a converter, we just state what we want to get as a
     * result. Think of a "cast" in typed programming languages, although a
     * ResourceConverter can do actually much more than that.
     */
    private String resultType;

    /**
     * OPTIONAL : in some cases the expected result resource type won't be
     * enough, you may need to be more specific. This argument refers to the
     * converter category, see
     * {@link org.squashtest.ta.framework.annotations.EngineComponent} and
     * {@link org.squashtest.ta.framework.components.ResourceConverter} for more
     * details.
     */
    private String converterCategory;

    /**
     * MANDATORY : the name of the resource newly created. Should be different
     * from any other resources already present in the context or face the risk
     * of an error at runtime.
     */
    private ResourceName resultName;

    /**
     * OPTIONAL : a non null but possibly empty collection of resource names.
     */
    private Collection<ResourceName> configuration = new LinkedList<ResourceName>();

    /**
     * Default constructor. Set instruction type to
     * {@link InstructionType#CONVERT}
     */
    public ConvertResourceInstruction() {
        setType(InstructionType.CONVERT);
    }

    @Override
    public void visit(TestInstructionVisitor visitor) {
        visitor.accept(this);
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public void addConfigurations(Collection<ResourceName> configurations) {
        this.configuration.addAll(configurations);
    }

    public ResourceName getResourceName() {
        return resourceName;
    }

    public void setResourceName(ResourceName resourceName) {
        this.resourceName = resourceName;
    }

    public String getConverterCategory() {
        return converterCategory;
    }

    public void setConverterCategory(String converterCategory) {
        this.converterCategory = converterCategory;
    }

    public ResourceName getResultName() {
        return resultName;
    }

    public void setResultName(ResourceName resultName) {
        this.resultName = resultName;
    }

    public Collection<ResourceName> getConfiguration() {
        return configuration;
    }
}
