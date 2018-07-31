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
package fr.henix.squash.framework.exception;

/**
 * Mother of all exceptions related to a test error due to misconfiguration or
 * misconception of the test.
 *
 * @author qtran
 */
public class BrokenTestException extends RuntimeException {

    /**
     *
     */
    
    public BrokenTestException() {
    }

    public BrokenTestException(String message) {
        super(message);
    }

    public BrokenTestException(Throwable cause) {
        super(cause);
    }

    public BrokenTestException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
