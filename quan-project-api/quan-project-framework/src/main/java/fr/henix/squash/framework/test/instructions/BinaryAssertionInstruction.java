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
 * A BinaryAssertionInstruction just holds what it takes to run a
 * {@link org.squashtest.ta.framework.components.BinaryAssertion}.
 *
 * <p>
 * It needs :</p>
 * <ul>
 * <li>the name of the resource containing the actual result</li>
 * <li>the name of the resource containing the expected result</li>
 * <li>the name of the assertion category ('equals' for instance)</li>
 * </ul>
 *
 * <p>
 * Additionally, the assertion may use optional or mandatory configuration. This
 * configuration is given as a list of names, each names referring to a
 * FileResource in the context.
 * </p>
 *
 * @see UnaryAssertionInstruction
 *
 * @author qtran
 */
public final class BinaryAssertionInstruction extends AbstractTestInstruction{

    /**
     * MANDATORY : The name of a resource. Basically it's the first argument of
     * the assertion, but most of the time it is referred to as the actual
     * result argument and should represent what had been produced by the system
     * under test.
     */
    private ResourceName actualResultName;

    /**
     * MANDATORY : The name of a resource that will be tested against the first
     * supplied resource. Basically it's the second argument of the assertion,
     * but most of the time it is referred to as the expected result argument
     * and should represent a resource with user-defined content.
     */
    private ResourceName expectedResultName;

    /**
     * MANDATORY : The assertion category. See
     * {@link org.squashtest.ta.framework.components.BinaryAssertion} and how
     * they define the category by respectively using the
     * {@link org.squashtest.ta.framework.annotations.TABinaryAssertion}
     * annotation.
     */
    private String assertionCategory;

    /**
     * OPTIONAL : a non null but possibly empty collection of resource names.
     */
    private Collection<ResourceName> assertionConfiguration = new LinkedList<ResourceName>();

    /**
     * Default constructor, set assertion mode to ASSERT by default. That means
     * the method {@link BinaryAssertionInstruction#continueOnFailOrError()}
     * will return false.
     */
    public BinaryAssertionInstruction() {
        setType(InstructionType.ASSERTION);
    }

    /**
     * Constructor which permits to set the assertion mode to use. If isVerify
     * is true then the assertion mode used is VERIFY and the method
     * {@link BinaryAssertionInstruction#continueOnFailOrError()} will return
     * true, else the assertion mode is ASSERT and the method
     * {@link BinaryAssertionInstruction#continueOnFailOrError()} will return
     * false.
     *
     * @param isVerify Used assertion mode.
     */
    public BinaryAssertionInstruction(boolean isVerify) {
        setType(InstructionType.ASSERTION);
        setContinueOnFailOrError(isVerify);
    }

    @Override
    public void visit(TestInstructionVisitor visitor) {
        visitor.accept(this);
    }

    public ResourceName getActualResultName() {
        return actualResultName;
    }

    public ResourceName getExpectedResultName() {
        return expectedResultName;
    }

    public String getAssertionCategory() {
        return assertionCategory;
    }

    public Collection<ResourceName> getAssertionConfiguration() {
        return assertionConfiguration;
    }

    public void setActualResultName(ResourceName actualResultName) {
        this.actualResultName = actualResultName;
    }

    public void setExpectedResultName(ResourceName expectedResultName) {
        this.expectedResultName = expectedResultName;
    }

    public void setAssertionCategory(String assertionCategory) {
        this.assertionCategory = assertionCategory;
    }

    public void addAssertionConfiguration(Collection<ResourceName> assertionConfiguration) {
        this.assertionConfiguration.addAll(assertionConfiguration);
    }
}
