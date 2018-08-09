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
 * Thrown by all component that take a resource as input when this input is not
 * correct. This exception is different from
 * {@link IllegalConfigurationException} in that it reports wrong input data,
 * not wrong configuration.
 *
 * @author qtran
 */
@SuppressWarnings("serial")
public class BadDataException extends BrokenTestException {

    /**
     * @param arg0
     * @param arg1
     */
    public BadDataException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public BadDataException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public BadDataException(Throwable arg0) {
        super(arg0);
    }

}
