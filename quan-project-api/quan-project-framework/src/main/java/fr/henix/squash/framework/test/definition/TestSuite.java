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

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * A TestSuite is defined as simple as possible : a name and a list of
 * {@link Ecosystem} to define tests in various conditions.<br>
 * It also contains informations (groupId, artifactId and version) on the Squash
 * TA maven project associtae to the current execution<br>
 * A test suite could also have parameters provided through the test suite
 * definition.
 *
 * @author qtran
 */
public class TestSuite implements Iterable<Ecosystem> {

    /**
     * Test suite name
     */
    private String name;

    /**
     * Associated TA maven project groupId
     */
    private String projectGroupId = "";

    /**
     * Associated TA maven project artifactId
     */
    private String projectArtifactId = "";

    /**
     * Associated TA maven project version
     */
    private String projectVersion = "";

    /**
     * Global parameters associated
     */
    private Properties globalParams = new Properties();

    /**
     * List of ecosystem of this test suite
     */
    private List<Ecosystem> suite = new LinkedList<Ecosystem>();

    /* ************************* getters ****************************** */
    public String getName() {
        return name;
    }

    public Iterator<Ecosystem> iterator() {
        return suite.iterator();
    }

    /**
     * Retrieve the groupId of the associated TA maven project
     *
     * @return TA maven project groupId
     */
    public String getProjectGroupId() {
        return projectGroupId;
    }

    /**
     * Retrieve the artifactId of the associated TA maven project
     *
     * @return TA maven project artifactId
     */
    public String getProjectArtifactId() {
        return projectArtifactId;
    }

    /**
     * Retrieve the version of the associated TA maven project
     *
     * @return TA maven project version
     */
    public String getProjectVersion() {
        return projectVersion;
    }

    /* ************************* adders ******************************** */
    public void setName(String name) {
        this.name = name;
    }

    public void addEcosystem(Ecosystem ecosystem) {
        suite.add(ecosystem);
    }

    /**
     * Set informations relative to the associated TA maven project
     *
     * @param groupId The TA maven project groupId
     * @param artifactId The TA maven project artifactId
     * @param version The TA maven project version
     */
    public void setProjectDescription(String groupId, String artifactId, String version) {
        projectGroupId = groupId;
        projectArtifactId = artifactId;
        projectVersion = version;
    }

    /**
     * Get all the targets used inside the test, in order to instantiate them
     *
     * @return a set of the unique target names used in the test
     */
    public Set<String> getTargetsNames() {
        Set<String> targetsNames = new HashSet<String>();
        for (Ecosystem ecosystem : suite) {
            Environment env = ecosystem.getEnvironment();
            if (env.getSetUp() != null) {
                targetsNames.addAll(env.getSetUp().getTargetsNames());
            }
            if (env.getTearDown() != null) {
                targetsNames.addAll(env.getTearDown().getTargetsNames());
            }
            for (Test test : ecosystem.getTestPopulation()) {
                targetsNames.addAll(test.getTargetsNames());
            }
        }
        return targetsNames;
    }

    /**
     * Retrieve the global parameters associated to the test suite
     *
     * @return The global parameters
     */
    public Properties getGlobalParams() {
        return globalParams;
    }

    /**
     * Set the global parameters associated to the test suite
     *
     * @param globalParams The new global parameters
     */
    public void setGlobalParams(Properties globalParams) {
        this.globalParams = globalParams;
    }
}
