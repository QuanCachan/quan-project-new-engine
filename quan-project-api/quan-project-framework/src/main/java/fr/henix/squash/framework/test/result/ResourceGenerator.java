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

import java.util.Properties;

/**
 * This class represents any element that may be generating Resources. This
 * interface is merely providing informations about the generator and does not
 * specify anything regarding their job.
 *
 * @author qtran
 */
public interface ResourceGenerator {

    /**
     * Gets the name of the generator.
     *
     * @return
     */
    String getName();

    /**
     * @return a textual representation of what kind of generator it is
     * (see @ResourceType)
     */
    String getGeneratorType();

    /**
     * Returns a Properties object which contains the current configuration of
     * the ResourceGenerator. These properties represent the effective configuration
     * once all configuration mean (default, config files, system properties etc) have been applied.
     *
     * @return
     */
    Properties getConfiguration();
}
