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
 * Interface defining the data composing the view of the test execution result
 * at its different levels (from whole suite to individual instructions)
 *
 * @param <S> subpart listed by the result part.
 *
 * @author qtran
 */
public interface CompositeResultPart<S extends ResultPart> extends ResultPart {

    /**
     * @return the list of result parts within this result part, if you need to
     * drill down.
     */
    List<? extends S> getSubpartResults();

    /**
     * @return the total number of tests
     */
    int getTotalTests();

    /**
     * @return how many tests halted for abnormal reasons.
     */
    int getTotalErrors();

    /**
     * @return how many tests failed due to SUT specs violations.
     */
    int getTotalFailures();

    /**
     * @return how many tests where either not run or halted.
     */
    int getTotalNotRun();

    /**
     * @return how many tests where not found.
     */
    int getTotalNotFound();

    /**
     * @return how many tests succeeded with a subsequent tearDown failure.
     * @deprecated since 1.6.0 : Warning status is no more used
     */
    int getTotalWarning();

    /**
     * @return how many tests succeeded
     */
    int getTotalSuccess();

    /**
     * @return how many tests passed.
     */
    int getTotalPassed();

    /**
     * @return how many tests didn't pass.
     */
    int getTotalNotPassed();
}
