package fr.henix.squash.framework.components;

import java.util.Collection;

/**
 * <p>
 * A ResourceConverter accepts a Resource of some type and returns the same
 * Resource somewhat casted in another type. Its job is twofold :</p>
 * <ul>
 * <li>convert the resource,</li>
 * <li>if needed, rate itself its relevance regarding the input.</li>
 * </ul>
 *
 * <p>
 * A new instance of this class will be created every time it is needed, so you
 * may safely make it stateful.</p>
 *
 * <p>
 * REMEMBER : annotate your converter with @EngineComponent, that denotes the
 * category it belongs to.</p>
 *
 * <hr>
 *
 * <h3>Not yet implemented but planned in future release : A note about
 * converter relevance</h3>
 *
 * <p>
 * As of version 1.0 and until further notice the following describes a
 * mechanism that is disabled in the current release, but included here anyway
 * for forward-compatibility and planning.</p>
 *
 * <p>
 * Most of the time a converter is chosen when it matches a conversion request
 * for a given the input resource type and output resource type. When zero or
 * one converter from resource type A to resource type B is present in the
 * engine at a given time for all pairs of (A,B), then there is no possible
 * ambiguity. However when multiple converters are mapped to the same input and
 * output types ambiguous situation happen.</p>
 *
 * <p>
 * In such case a vote is casted among the converters. Each converter will be
 * supplied with the actual input resource and must check it contents to tell if
 * it can do something from it or not. They then rate their relevance as a float
 * in [0,1]. The engine then picks the best score. So it's up to the implementor
 * to make the most fair estimation of whether that converter, given the
 * resource content, fits perfectly, mildly or not at all.
 * </p>
 *
 * <p>
 * Note that even with this system the conversion may still fail because :</p>
 * <ol>
 * <li>the voting system was disabled (default)</li>
 * <li>the elected converter still fails</li>
 * </ol>
 *
 * <p>
 * When the conversion fails the user still may change its instructions in order
 * to specify the Converter category (see @EngineComponent) to specifically call
 * the converter matching the input type, output type, and converter
 * category.</p>
 *
 * @author qtran
 */
public interface ResourceConverter<INPUT extends Resource<INPUT>, OUTPUT extends Resource<OUTPUT>> {

    /**
     * <p>
     * (OPTIONAL) A ResourceConverter, if asked to, will reply if it knows the
     * content of the file. This operation is optional and is invoked when
     * ambiguous cases arise (see class documentation for details ). In
     * ambiguous situations the result of this method contributes to the
     * decision of whether that converter shall be used or not.</p>
     *
     * <p>
     * The method simply checks the resource and tells if it knows the content.
     * The result is a floating value between 0 and 1, rating its confidence in
     * being the most relevant converter for the task. If a converter cannot
     * work because it doesn't have the proper configuration (see
     * {@link #addConfiguration(Collection)}, it must not throw exceptions but
     * return 0 instead.</p>
     *
     * <p>
     * Note that a ResourceConverter may be polled that way and its response
     * ignored, and conversely it could be asked to instantiate the resource
     * while not having been polled. Yup, life's hard. In other words, a
     * converter may be eventually invoked even when it rates itself 0.0f if the
     * user choose to do so.</p>
     *
     * @param input the target resource.
     * @return a float in [0,1] (inclusive)
     */
    float rateRelevance(INPUT input);

    /**
     * <p>
     * Some ResourceConverter may need additional tools to perform their tasks.
     * Basically it's about things that may help them to interpret the data
     * their are fed with. If the converter needs additional configuration to
     * use {@link #rateRelevance(Resource)} they should (and will) be passed
     * first.
     * </p>
     * <p>
     * If this methods detects bad configuration, it should throw
     * {@link org.squashtest.ta.framework.exception.BadDataException} (for
     * blocking errors only, like missing data. As of version 1.0.0, useless or
     * unknown configuration resources can be ignored, albeit with warning, of
     * other data define a usable configuration)
     * </p>
     *
     * @param configuration
     */
    void addConfiguration(Collection<Resource<?>> configuration);

    /**
     * <p>
     * Given a Resource, will convert to a Resource of the type it's made for.
     * </p>
     * <p>
     * If some anomaly in the INPUT resource is detected, this method should
     * throw {@link org.squashtest.ta.framework.exception.BadDataException}
     * </p>
     * <p>
     * If a converter cannot work because it doesn't have the proper
     * configuration (see {@link #addConfiguration(Collection)}, it must throw
     * an
     * {@link org.squashtest.ta.framework.exception.IllegalConfigurationException}.
     * </p>
     * <p>
     * For any other error, please throw
     * {@link org.squashtest.ta.framework.exception.InstructionRuntimeException}
     * (meaning the error is not due to data, but to some other, probably
     * environment related, problem)
     * </p>
     *
     * @param resource
     * @return
     */
    OUTPUT convert(INPUT resource);

    /**
     * Once it has been ran, a ResourceConverter doesn't need its configuration
     * anymore. Clean it.
     *
     */
    void cleanUp();
}
