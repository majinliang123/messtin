package org.messtin.framework.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.messtin.framework.core.exception.CoreException;
import org.messtin.framework.core.exception.CoreInitException;

/**
 * The starter of the project
 *
 * @author majinliang
 */
public final class Core {
    private static final Logger logger = LogManager.getLogger(Core.class);

    /**
     * Show if the project initialized.
     * Its default value is {@code false}.
     */
    private static boolean INITIALIZED = false;

    public static void init(String... packets) throws Exception {
        logger.info("Start messtin core");
        if (!INITIALIZED) {
            Init.init(packets);
            setInitialized();
            logger.info("Initialize messtin core successfully. And set `INITIALIZED` as true.");
        } else {
            throw new CoreInitException("The project has been initialized.");
        }
    }

    private static void setInitialized() {
        INITIALIZED = true;
    }
}
