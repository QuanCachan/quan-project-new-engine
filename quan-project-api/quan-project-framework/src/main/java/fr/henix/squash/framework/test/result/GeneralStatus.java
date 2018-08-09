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

/**
 * <p>
 * The GeneralStatus gives a global overview of the status of a TestSuite, a
 * Test, or a TestInstruction.</p>
 *
 * <p>
 * The four statuses are :</p>
 * <ul>
 * <li>SUCCESS : the given
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} or
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} element went
 * all fine all along.</li>
 * <li><span style="text-decoration-line:line-through;">
 * WARNING : the given
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} or
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} element
 * succeded, but some non-blocking operation (ex: a tear down step) went
 * wrong.</span>
 * Deprecated since 1.6.0: warning status is no more used</li>
 * <li>NOT_RUN : the given
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} or
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} element was
 * never ran or ended before actual expectation testing.</li>
 * <li>FAIL : the given
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} or
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} element
 * encountered a business error (the SUT does not fulfill the
 * expectations).</li>
 * <li>NOT_FOUND : the given
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} or
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} element was not
 * found and so not executed.</li>
 * <li>ERROR : the given
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} or
 * {@link org.squashtest.ta.framework.test.definition.TestSuite} element
 * encountered a technical error (from any origin). It means the SUT call
 * hung.</li>
 * </ul>
 * <p>
 * Those statuse are sorted by increasing importance. For instance if a test
 * suite contains 3 tests of which 2 have status 'SUCCESS' and 1 has status
 * 'NOT_RUN', the resulting suite status is 'NOT_RUN'.
 * </p>
 *
 * @author qtran
 */
public enum GeneralStatus {

    /**
     * This status denotes complete success for a
     * {@link org.squashtest.ta.framework.test.definition.TestSuite} element.
     */
    SUCCESS() {
        @Override
        public boolean isPassed() {
            return true;
        }
    },
    /**
     * This status denotes partial success for a
     * {@link org.squashtest.ta.framework.test.definition.TestSuite} element:
     * the test is considered as passed, but some non-blocking operation failed
     * (ex: a teardown step).
     *
     * @deprecated since 1.6.0 : Warning status is no more used in Squash TA
     */
    WARNING() {

        @Override
        public boolean isPassed() {
            return true;
        }

    },
    /**
     * This status denotes that a
     * {@link org.squashtest.ta.framework.test.definition.TestSuite} element has
     * not been tested.
     */
    NOT_RUN() {
        @Override
        public boolean isPassed() {
            return false;
        }
    },
    /**
     * This status denotes an ongoing test execution.
     */
    RUNNING() {
        @Override
        public boolean isPassed() {
            return false;
        }
    },
    /**
     * This status denotes a failure of a
     * {@link org.squashtest.ta.framework.test.definition.TestSuite} element to
     * meet the expectations.
     */
    FAIL() {
        @Override
        public boolean isPassed() {
            return false;
        }
    },
    /**
     * This status denotes that a test of a
     * {@link org.squashtest.ta.framework.test.definition.TestSuite} hasn't been
     * found in the project
     */
    NOT_FOUND() {
        @Override
        public boolean isPassed() {
            return false;
        }
    },
    /**
     * This status denotes a technical failure during the
     * {@link org.squashtest.ta.framework.test.definition.TestSuite} execution.
     */
    ERROR() {
        @Override
        public boolean isPassed() {
            return false;
        }
    };

    /**
     * Returns the most severe status between this one and the other.
     *
     * @param otherStatus a status to compare.
     * @return the most severe status of the two.
     */
    public GeneralStatus mostSevereStatus(GeneralStatus otherStatus) {
        return (moreSevere(otherStatus)) ? this : otherStatus;
    }

    /**
     * Compares this status to an other to know which is the more severe.
     *
     * @param otherStatus the status we want to compare to this one.
     * @return <code>true</code> if this {@link GeneralStatus} is more severe
     * than the other status
     */
    public boolean moreSevere(GeneralStatus otherStatus) {
        return (this.ordinal() > otherStatus.ordinal());
    }

    public abstract boolean isPassed();

    public boolean isFailOrError() {
        return isError() || isFail();
    }

    public boolean isError() {
        return this.equals(ERROR);
    }

    public boolean isFail() {
        return this.equals(FAIL);
    }

    public boolean isSuccess() {
        return this.equals(SUCCESS);
    }

    public boolean isNotRun() {
        return this.equals(NOT_RUN);
    }

    public boolean isNotFound() {
        return this.equals(NOT_FOUND);
    }
}
