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
package fr.henix.squash.framework.components;

import fr.henix.squash.framework.annotations.TAResource;
import fr.henix.squash.framework.exception.InstructionRuntimeException;
import fr.henix.squash.framework.tools.TempDir;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base resource implementation.
 *
 * @author qtran
 */
@TAResource("file")
public class FileResource implements Resource<FileResource> {

    private static final String DEFAULT_PREFIX = "_ta_";

    private static final Logger LOGGER = LoggerFactory.getLogger(FileResource.class.getName());

    private static final int COPY_BUFFER_SIZE = 1024;

    private File file;

    private List<InputStream> openedStreams = new ArrayList<InputStream>();

    public FileResource(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public FileResource copy() {
        File copyFile = null;
        try {
            copyFile = createTempCopyFile(file);
            dump(copyFile, true);
        } catch (IOException ioe) {
            InstructionRuntimeException ex = new InstructionRuntimeException(
                    "could not copy resource , because of "
                    + "the platform itself. The operating system may block access to certain resource, please copy this message"
                    + "and forward it to your administrator.", ioe);
            throw ex;
        }

        return new FileResource(copyFile);
    }

    @Override
    public void cleanUp() {
        closeStream();
    }

    private void closeStream() {
        for (InputStream stream : openedStreams) {
            try {
                stream.close();
            } catch (IOException e) {
                LOGGER.info("closing file resource opened stream failed.", e);
            }
        }
    }

    /**
     * Get an open input stream on the encapsulated file.
     *
     * @return
     */
    public InputStream openStream() {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            openedStreams.add(inputStream);
            return inputStream;
        } catch (FileNotFoundException e) {
            throw new InstructionRuntimeException("FileResource could not be opened for reading.", e);
        }

    }

    protected File createTempCopyFile(File originalFile) throws IOException {
        File copyFile;
        String fileName = originalFile.getAbsoluteFile().getName();
        int extensionIndex = fileName.lastIndexOf('.');
        String prefix;
        String suffix;
        if (extensionIndex >= 0) {
            prefix = fileName.substring(0, Math.min(20, extensionIndex));
            suffix = fileName.substring(extensionIndex, fileName.length());
        } else {
            prefix = fileName.substring(0, Math.min(20, fileName.length()));
            suffix = "temp";
        }
        if (prefix.length() < 3) {
            prefix += DEFAULT_PREFIX;
        }
        copyFile = File.createTempFile(prefix, suffix, TempDir.getExecutionTempDir());
        return copyFile;
    }

    /**
     * Dump the resource contents in a given location.
     *
     * @param destination
     * @param temporary
     * @throws java.io.IOException if the io operation fails.
     */
    public void dump(File destination, boolean temporary) throws IOException {
        copyInternal(destination, file, new HashSet<File>(), temporary);
    }

    private void copyInternal(File copyFile, File source, Set<File> treated, boolean temporary) throws IOException {
        if (source.isDirectory()) {
            ensureDestinationDirectoryExists(copyFile);
            for (File child : source.listFiles()) {
                if (!treated.contains(child)) {//guards against cycles created by symlinks
                    treated.add(child);
                    copyInternal(new File(copyFile, child.getName()), child, treated, temporary);
                }
            }
        } else {
            LOGGER.debug("Copying {} to {}", source.getAbsolutePath(), copyFile.getAbsolutePath());
            copyFile(source, copyFile);
        }
    }

    protected void ensureDestinationDirectoryExists(File destinationDir) throws IOException {
        if (destinationDir.exists() && !destinationDir.isDirectory()) {
            boolean deleted = destinationDir.delete();
            if (!deleted) {
                throw new IOException("Failed to delete newly created regular temp file to replace it by a directory!");
            }
        }
        boolean dirCreated = destinationDir.mkdir() || destinationDir.exists();
        if (!dirCreated) {
            throw new IOException("Failed to delete create temp directory!");
        }
    }

    protected void copyFile(File src, File dest) throws IOException {

        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(src);
            output = new FileOutputStream(dest);

            byte[] buf = new byte[COPY_BUFFER_SIZE];
            int len;
            while ((len = input.read(buf)) > 0) {
                output.write(buf, 0, len);
            }
        } finally {
            safeStreamCloser(input, output);
        }
    }

    private void safeStreamCloser(InputStream in, OutputStream out) throws IOException {
        try {
            if (in != null) {
                in.close();
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }
}
