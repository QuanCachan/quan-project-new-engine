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
 * This class contains the result of a Target initialisation
 *
 * @author qtran
 */
public class TargetInitialisationResult {

    private String name;
    private String nature;

    private TargetStatus status;

    /**
     * @param name The name of the target
     * @param nature The nature name of the target
     * @param status The result of the initialisation
     */
    public TargetInitialisationResult(String name, String nature, TargetStatus status) {
        this.name = name;
        this.nature = nature;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getNature() {
        return nature;
    }

    public TargetStatus getStatus() {
        return status;
    }

    /**
     * The result status of a target initialisation. "not found" means the
     * target was not found or was not properly configured. "error" means an
     * error occurs during the target initialisation. "ok" means the target was
     * properly configured.
     *
     */
    public enum TargetStatus {

        OK, NOT_FOUND, ERROR;
    }
}
