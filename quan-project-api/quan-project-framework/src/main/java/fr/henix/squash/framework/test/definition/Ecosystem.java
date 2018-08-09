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
package fr.henix.squash.framework.test.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * This object represents a group of test to be executed in a same environment.
 *
 * @author qtran
 */
public class Ecosystem {

    /**
     * Execution environment for test execution in this ecosystem.
     */
    private Environment environment;

    /**
     * Tests to be executed in the environment defined by this ecosystem.
     */
    private List<Test> testPopulation = new ArrayList<Test>();

    /**
     * The name of this ecosystem.
     */
    private String name;

    /**
     * @return The name of this ecosystem.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Execution environment for test execution in this ecosystem.
     */
    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * @return Tests to be executed in the environment defined by this
     * ecosystem.
     */
    public List<Test> getTestPopulation() {
        return testPopulation;
    }

    public void setTestPopulation(List<Test> testPopulation) {
        this.testPopulation = testPopulation;
    }

    /**
     * Enumeration for the ecosystem phase
     */
    public enum EcosysPhase {
        SETUP, RUN, TEARDOWN;

        /**
         * @return <code>true</code> if the {@link EcosysPhase} is a setup
         * ecosystem phase
         */
        public boolean isEcosystemSetup() {
            return this.equals(SETUP);
        }

        /**
         * @return <code>true</code> if the {@link EcosysPhase} is a run
         * ecosystem phase
         */
        public boolean isEcosystemRun() {
            return this.equals(RUN);
        }

        /**
         * @return <code>true</code> if the {@link EcosysPhase} is a teardown
         * ecosystem phase
         */
        public boolean isEcosystemTeardown() {
            return this.equals(TEARDOWN);
        }

    }
}
