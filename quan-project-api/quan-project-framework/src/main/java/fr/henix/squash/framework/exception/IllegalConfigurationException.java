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
package fr.henix.squash.framework.exception;

/**
 * Exception thrown by an @EngineComponent when the configuration transmitted by
 * the test is not correct.
 *
 * @author qtran
 */
@SuppressWarnings("serial")
public class IllegalConfigurationException extends BrokenTestException {

    /**
     * Complete initialization constructor.
     *
     * @param arg0 detail message to describe the problem.
     * @param arg1 {@link Throwable} thrown while trying to use the
     * configuration resources.
     */
    public IllegalConfigurationException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * Constructor used if a configuration anomaly is detected but no particular
     * throwable was produced by the analysis code.
     *
     * @param arg0 message to explain what the configuration anomaly is.
     */
    public IllegalConfigurationException(String arg0) {
        super(arg0);
    }
}
