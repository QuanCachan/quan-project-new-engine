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
 * Exception thrown when a command cannot be applied because it is not found.
 *
 * @author qtran
 */
public class CannotApplyCommandException extends BrokenTestException {

    /**
     *
     */
    private static final long serialVersionUID = 9049891576601114776L;

    public CannotApplyCommandException() {
        super();
    }

    public CannotApplyCommandException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public CannotApplyCommandException(String arg0) {
        super(arg0);
    }
}
