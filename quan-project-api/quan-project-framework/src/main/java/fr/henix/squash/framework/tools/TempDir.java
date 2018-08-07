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
package fr.henix.squash.framework.tools;

import java.io.File;

/**
 * This class deals with the management of all the temporary files (cf Mantis
 * 2080). This is a generic class as far as possible, which works hand in hand
 * with core-utils TempFileUtils (which does the dirty jobs)
 *
 * It contains only static methods.
 *
 * @author qtran
 */
public final class TempDir {

    private static File mainTempDir;

    public static final String DEFAULT_DIR_NAME = "default";
    public static final String MACRO_DIR_NAME = "macros";

    //FIXME static variables trigger extremely nasty bugs and should be avoided at all cost!
    private static File executionTempDir;
    //FIXME static variables trigger extremely nasty bugs and should be avoided at all cost!
    private static File macroTempDir;
    //FIXME static variables trigger extremely nasty bugs and should be avoided at all cost!
    private static File defaultTempDir;

    public TempDir() {
    }

    /**
     *
     * @return the directory where the temporary files created by the test must
     * be put
     */
    public static File getExecutionTempDir() {
        if (TempDir.executionTempDir == null) {
            TempDir.executionTempDir = getDefaultTempDir();
        }
        //we create the directory only if we need it
        TempDir.executionTempDir.mkdirs();
        return TempDir.executionTempDir;
    }

    /**
     * Method used by TempFileUtils to determine the actual temporary directory
     *
     * @param executionTempDir
     */
    public static void setExecutionTempDir(File executionTempDir) {
        TempDir.executionTempDir = executionTempDir;
    }

    /**
     * @return the directory where the macros files must be put
     */
    public static File getMacroTempDir() {
        if (TempDir.macroTempDir == null) {
            TempDir.macroTempDir = new File(getDefaultTempDir(), MACRO_DIR_NAME);
        }
        // we create the directory only if we need it
        TempDir.macroTempDir.mkdirs();
        return TempDir.macroTempDir;
    }

    public static void setMacroTempDir(File macroTempDir) {
        TempDir.macroTempDir = macroTempDir;
    }

    /**
     *
     * @return the default temp directory, used for the files created during a
     * mvn install (as the groovy files)
     */
    public static File getDefaultTempDir() {
        if (defaultTempDir == null) {
            defaultTempDir = new File(getMainTempDir(), DEFAULT_DIR_NAME);
        }
        defaultTempDir.mkdirs();
        return defaultTempDir;
    }

    /**
     *
     * @return the main directory where all the temporary files must be created
     */
    public static File getMainTempDir() {
        if (TempDir.mainTempDir == null) {
            TempDir.mainTempDir = buildDefaultTempDir(System.getProperty("java.io.tmpdir"));
        }
        return TempDir.mainTempDir;
    }

    /**
     * Create the main temp directory path from a given path (
     * {@literal <path>/Squash_TA} and actually build the directory
     *
     * @param mainTempDir : path where the main temp directory should be created
     */
    public static void setMainTemDir(String mainTempDir) {
        TempDir.mainTempDir = buildDefaultTempDir(mainTempDir);
    }

    // we are bound to define it here, as mvn install uses it before passing in the Mojos class
    private static File buildDefaultTempDir(String mainPath) {
        /**
         * the output of "System.getProperty("java.io.tmpdir")" ends with a
         * separator in Windows, but not in Linux or Mac ! So we need this
         * little fix
         */
        StringBuilder path = new StringBuilder(mainPath);
        if (path.charAt(path.length() - 1) != File.separatorChar) {
            path.append(File.separatorChar);
        }
        path.append("Squash_TA");
        File file = new File(path.toString());
        file.mkdirs();
        return file;
    }

}
