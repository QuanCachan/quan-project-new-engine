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
 * This assertion class is used to report assertions failures from contexts
 * where the engine component context is not available.
 * <p>
 * This will be reported as comparison ERROR, not functional FAIL, which means
 * that if the case is in fact a functional mismatch you'll have to catch this
 * exception in the assertion test() method and encapsulate in it the
 * appropriate {@link Exception} (see the UnaryAssertion and BinaryAssertion
 * interfaces).</p>
 *
 * @author qtran
 */
public class TestAssertionFailure extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = -7756671686389854106L;

    public TestAssertionFailure() {
    }

    public TestAssertionFailure(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public TestAssertionFailure(String arg0) {
        super(arg0);
    }

    public TestAssertionFailure(Throwable arg0) {
        super(arg0);
    }
}
