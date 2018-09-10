package org.messtin.framework.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.messtin.framework.core.util.ClassUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Init the whole project.
 *
 * @author majinliang
 */
public final class Init {
    private static final Logger logger = LogManager.getLogger(Init.class);

    public static void init(String... packets) throws Exception {
        logger.info("Initializing messtin core.");
        Set<Class<?>> clazzs = new HashSet<>();
        for (String packet : packets) {
            clazzs.addAll(ClassUtil.getClasses(packet));
        }
    }
}
