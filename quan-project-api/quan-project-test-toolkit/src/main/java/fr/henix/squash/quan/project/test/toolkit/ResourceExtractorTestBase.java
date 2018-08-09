package fr.henix.squash.quan.project.test.toolkit;

import fr.henix.squash.framework.tools.TempDir;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to test file-dependant components while easily deploying the
 * test files from test resources, AND cleaning them up after test.
 *
 * This class is intended for people who don't want their project to get
 * ear-pointy, cold and over-refined ;). If you're not one of them, look at
 * <code>org.squashtest.ta.api.test.toolkit.ResourceExtractorSpockBase</code>
 * instead.
 *
 * @author qtran
 */
public class ResourceExtractorTestBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceExtractorTestBase.class);

    private static final Set<File> fileErasingFailedAtTestCleanup = new HashSet<>();
    private final Set<File> extractedResources = new HashSet<>();

    /**
     * This method creates an empty temporary file and adds it to the list of
     * files to wipe. This can be used to create desintallation files when the
     * tested component creates a result file.
     *
     * @return the created file path as a File object.
     * @throws IOException
     */
    public File createNtrackFile() throws IOException {
        return createTempFile();
    }

    private File createTempFile() throws IOException {
        return File.createTempFile("rsc", "tmp", TempDir.getExecutionTempDir());
    }

    /**
     * This is the static version of {@link #createNtrackFile() }
     *
     * @return the created empty temporary file.
     * @throws IOException
     */
    public static File createFile4Class() throws IOException {
        File f = new ResourceExtractorTestBase().createNtrackFile();
        fileErasingFailedAtTestCleanup.add(f);
        return f;
    }

    /**
     * Call this method from your daughter test class when it needs to deploy a
     * packaged resource (eg : part of src/test/resource) as a temporary file.
     *
     * @param resource the name of the resource to deploy.
     * @return the reference of the temporary file.
     * @throws IOException
     */
    protected File createFile(String resource) throws IOException {
        File f = createTempFile();
        extractToFile(f, resource);
        return f;
    }

    /**
     * Call this method from your daughter class when it needs to deploy a
     * packaged resource AND choose the location and name of the created file.
     *
     * @param f the destination file.
     * @param resource the resource name.
     * @throws IOException
     * @throws FileNotFoundException if the parent directory of the specified
     * destination , file does not exist.
     */
    protected void extractToFile(File f, String resource) throws IOException {
        extractedResources.add(f);
        final Class<? extends ResourceExtractorTestBase> aClass = getClass();
        try (
                InputStream rsc = aClass.getResourceAsStream(resource);
                OutputStream os = new FileOutputStream(f);) {
            if (rsc == null) {
                throw new MissingResourceException("Resource not found, it cannot be extracted.", getClass().getName(), resource);
            }
            byte[] buffer = new byte[4096];
            int read = rsc.read(buffer);
            while (read >= 0) {
                os.write(buffer, 0, read);
                read = rsc.read(buffer);
            }
        }
    }

    /**
     * This version of the targeted file extractor method may be called without
     * fuss from a static context AND allow the file to be automatically swept
     * at the end of the test.
     *
     * @see ResourceExtractorTestBase#extractToFile(java.io.File,
     * java.lang.String)
     * @param f the destination file
     * @param resource the resource name
     * @throws FileNotFoundException
     * @throws IOException
     */
    protected static void extractToFile4Class(File f, String resource) throws FileNotFoundException, IOException {
        new ResourceExtractorTestBase().extractToFile(f, resource);
    }

    /**
     * This version of the temporary file extractor method may be called without
     * fuss from a static context AND allow the file to be automatically swept
     * at the end of the test.
     *
     * @see ResourceExtractorTestBase#createFile(java.lang.String)
     * @param resource the resource name
     * @return the reference of the temporary file
     * @throws FileNotFoundException
     * @throws IOException
     */
    protected static File createFile4Class(String resource) throws IOException {
        File f = new ResourceExtractorTestBase().createFile(resource);
        fileErasingFailedAtTestCleanup.add(f);
        return f;
    }

    /**
     * This method is called by the junit framework after each test to remove
     * the files it created through this resource manager instance.
     */
    @After
    public void removeTestFiles() {
        for (File f : extractedResources) {
            if (f.delete()) {
                LoggerFactory.getLogger(getClass()).debug(f.getAbsolutePath() + " deleted OK.");
            } else {
                LoggerFactory.getLogger(getClass()).warn(f.getAbsolutePath() + " deletion failed after test.");
                fileErasingFailedAtTestCleanup.add(f);
            }
        }
    }

    /**
     * This method is called by the junit framework after each test to remove
     * the files it created through this resource manager class. It will also
     * try to reclaim files that were not successfully destroyed by the
     * {@link ResourceExtractorTestBase#removeTestFiles()}
     */
    @AfterClass
    public static void removeFailedTestFiles() {
        for (File f : fileErasingFailedAtTestCleanup) {
            if (f.delete()) {
                LOGGER.debug(f.getAbsolutePath() + " failed at test cleanup but deleted OK at class cleanup.");
            } else {
                LOGGER.warn(f.getAbsolutePath() + " deletion failed definitely.");
                fileErasingFailedAtTestCleanup.add(f);
            }
        }
    }

    /**
     * This method creates a file derived from the input file by replacing its
     * EOL marks by the current platform standard EOL mark. Use this with care,
     * because it might hide tested class defacts. However, if the class under
     * test is supposed to create files with the default EOL, this will make
     * using static expected file contents easier by adjusting them to the
     * current platform default. One caveat : this will add a final EOL mark
     * even if the input file had none.
     *
     * @param f input file
     * @return a file with the same content as the f input file, except EOL
     * which are replaced by the platform current EOL.
     * @throws IOException
     */
    protected File toPlatformEndOfLine(File f) throws IOException {
        File dest = createTempFile();
        extractedResources.add(dest);
        try (
                BufferedReader reader = new BufferedReader(new FileReader(f));
                PrintWriter writer = new PrintWriter(dest);) {
            String line = reader.readLine();
            while (line != null) {
                writer.println(line);
                line = reader.readLine();
            }
        }
        return dest;
    }

    /**
     * This static function allows the use of
     * {@link #toPlatformEndOfLine(java.io.File)} from a static context.
     *
     * @param f the input file
     * @see #toPlatformEndOfLine(java.io.File)
     * @return the result of EOL replacement.
     * @throws IOException
     */
    protected static File toPlatformEndOfLine4Class(File f) throws IOException {
        File dest = new ResourceExtractorTestBase().toPlatformEndOfLine(f);
        fileErasingFailedAtTestCleanup.add(dest);
        return dest;
    }

    /**
     * This method asserts that actual and expected contents match. The first
     * detected line difference is shown in the test failure message with the
     * line number.
     *
     * @see #checkActualContentAgainstExpected(java.io.File, java.io.File)
     * @param actual actual file to test.
     * @param expectedResourceName name of a test resource which defines the
     * expected file content.
     * @throws IOException
     */
    protected void checkActualContentAgainstExpected(File actual, String expectedResourceName) throws IOException {
        File expected = createFile(expectedResourceName);
        checkActualContentAgainstExpected(actual, expected);
    }

    /**
     * This method asserts that actual and expected contents match. The first
     * detected line difference is shown in the test failure message with the
     * line number.
     *
     * @see #checkActualContentAgainstExpected(java.io.File, java.lang.String)
     * @param actual actual file to test.
     * @param expected file with the expected contents.
     * @throws IOException
     */
    protected void checkActualContentAgainstExpected(File actual, File expected) throws IOException {
        try (final BufferedReader actualReader = new BufferedReader(new FileReader(actual)); final BufferedReader expectedReader = new BufferedReader(new FileReader(expected))) {
            String actualLine = actualReader.readLine();
            List<String> actualContent = new ArrayList<>();
            while (actualLine != null) {
                LOGGER.debug(actualLine);
                actualContent.add(actualLine);
                actualLine = actualReader.readLine();
            }
            int line = 1;
            for (String currentLine : actualContent) {
                assertEquals("Line " + line + " diff:", expectedReader.readLine(), currentLine);
                line++;
            }
            
            //Assert End of file (whitespaces...)
            assertEquals("Line " + line + " diff:", expectedReader.readLine(), actualLine);
            assertNull("Actual is shorter than expected", expectedReader.readLine());
        }
    }

}
