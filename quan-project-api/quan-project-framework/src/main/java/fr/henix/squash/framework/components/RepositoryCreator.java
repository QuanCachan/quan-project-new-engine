package fr.henix.squash.framework.components;

import java.net.URL;

/**
 * A RepositoryCreator is meant to configure instances of specific
 * implementations of ResourceRepository. It goal is twofold :
 * <ol>
 * <li>Given a File on a properties file, it must tell whether it can create the
 * resource or not, by analyzing the content</li>
 * <li>If it accepts the job, it should return a properly configured
 * ResourceRepository</li>
 * </ol>
 *
 * In that later sense we have here some weak factory.
 *
 * <p>
 * A unique instance of each implementation will be created at the engine init
 * phase, then it will be discarded.</p>
 *
 * <p>
 * Remember : annotate your classes using @EngineComponent, though the string
 * argument will not be meaningful here.</p>
 *
 * @author qtran
 */
public interface RepositoryCreator<REPOSITORY extends ResourceRepository> {

    /**
     * Test if that repository creator handles a given repository definition.
     *
     * @param propertiesURL URL for the resource repository definition to
     * assess.
     * @return <code>true</code> if this repository creator can handle the
     * definition URL, <code>false</code> if not.
     */
    boolean canInstantiate(URL propertiesURL);

    /**
     * Given a File on a properties file, will set up an instance of a specific
     * ResourceRepository and return it. Warning : this method may return
     * successfully even if the given Properties does not match the
     * ResourceRepository, if all (if any) of his required properties are set by
     * pure chance or not.
     *
     * @param propertiesURL the URL of the repository definition file.
     * @return a repository instance.
     * @throws org.squashtest.ta.framework.exception.BrokenTestException if the
     * @param propertiesFile URL is not valid for this RepositoryCreator. The
     * transmitted URL is supposed to have been tested with
     * {@link #canInstantiate(URL)}
     */
    REPOSITORY createRepository(URL propertiesURL);
}
