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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.henix.squash.framework.test.result;

import fr.henix.squash.framework.test.instructions.ResourceName;

/**
 *
 * While a {@link org.squashtest.ta.framework.components.Resource} states how
 * the data are organized and what operations you may perform on them, the
 * ResourceMetadata gives a view on the Resource in its use context. A
 * ResourceMetadata will be paired to a Resource when a TestInstruction failed.
 *
 * @author qtran
 */
public interface ResourceMetadata {

    public enum ResourceRole {
        /**
         * Command/Converter input
         */
        INPUT,
        /**
         * Assertion actual result
         */
        ACTUAL_RESULT,
        /**
         * Assertion expected result
         */
        EXPECTED_RESULT,
        /**
         * Instruction configuration element
         */
        CONFIGURATION,
        /**
         * Execution report transmitted from within a component
         */
        EXECUTION_REPORT
    }

    /**
     * @return the effective name of the Resource instance.
     */
    ResourceName getName();

    /**
     * A {@link ResourceGenerator} says from where a given Resource is from.
     * Most of the time it is a
     * {@link org.squashtest.ta.framework.components.ResourceRepository}, a
     * {@link org.squashtest.ta.framework.components.Target}.
     *
     * @return
     */
    ResourceGenerator getOrigin();

    /**
     * @return the textual name of the Resource type. (the value of the
     * @ResourceType annotation on the top of the Resource definition).
     */
    String getResourceType();

    /**
     * @return explain what was the purpose of the resource in the context of
     * the instruction.
     */
    ResourceRole getRole();
}
