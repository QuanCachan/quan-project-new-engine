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
package fr.henix.squash.framework.test.instructions;

/**
 * The name under which a resource will be registered.
 *
 * @author qtran
 */
public class ResourceName {

    /**
     * The scope of a resource is relevant when it imports/creates/defines
     * resources. When the scope is set to SCOPE_TEST, the resource will exist
     * throughout the whole test, when the scope is set to temporary the
     * resource will be accessible to the instructions in the same local context
     * only.
     *
     */
    public static enum Scope {
        /**
         * The resource is defined for the duration of ecosystem execution.
         * Intended for ecosystem environment related resources, such as handles
         * for helper processes. Ecosystem resources should be created only
         * during execution of the ecosystem setup script.
         */
        SCOPE_ECOSYSTEM,
        /**
         * The resource is defined until the end of the test.
         */
        SCOPE_TEST,
        /**
         * The resource is defined until the next
         * {@link ResetLocalContextInstruction} instruction.
         */
        SCOPE_TEMPORARY,
        /**
         * The resource is defined by a failure report (and as such local to its
         * execution details)
         */
        FAILURE_REPORT,
        /**
         * The resource is defined by the framework to define special values
         */
        SCOPE_BUILTIN;
    }

    /**
     * Scope of the resource name.
     */
    private Scope scope = Scope.SCOPE_TEST;
    /**
     * Resource name.
     */
    private String name;

    private static final int HASHCODE_PRIME_COEFFICIENT = 3;

    /**
     *
     * @param nameValue
     */
    public ResourceName(String nameValue) {
        this.name = nameValue;
        this.scope = Scope.SCOPE_TEST;
    }

    public ResourceName(Scope nameScope, String nameValue) {
        this.scope = nameScope;
        this.name = nameValue;
    }

    @Override
    public boolean equals(Object obj) {
        /* same => equals of course */
        if (this == obj) {
            return true;
        }

        /* null object or different type => can't be equals */
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        ResourceName objResource = (ResourceName) obj;
        if (scope != objResource.scope) {
            return false;
        }

        if (name == null && objResource.name == null) {
            return true;
        } else {
            return name != null && name.equals(objResource.name);
        }
    }

    @Override
    public int hashCode() {
        int hash = scope==null?0:scope.hashCode();
        hash += (name==null?0:name.hashCode())*HASHCODE_PRIME_COEFFICIENT;
        return hash;
    }

    public Scope getScope() {
        return scope;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return scope.name() + ":" + name;
    } 
}
