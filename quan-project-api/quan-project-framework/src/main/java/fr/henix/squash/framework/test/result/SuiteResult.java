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
package fr.henix.squash.framework.test.result;

import java.util.List;

/**
 * After a {@link org.squashtest.ta.framework.test.definition.TestSuite} had
 * been executed, the engine will return a SuiteResult, this object holds basic
 * statistics regarding the test suite execution, detail can be found in the
 * attached {@link TestResult} objects.
 *
 * @author qtran
 */
public interface SuiteResult extends CompositeResultPart<EcosystemResult> {

    /**
     * @return project groupId
     */
    String getProjectGroupId();

    /**
     * @return project artifactId
     */
    String getProjectArtifactId();

    /**
     * @return project version
     */
    String getProjectVersion();

    /**
     * @return set of target initialisation results
     */
    List<TargetInitialisationResult> getTargetInitialisationResults();
}
