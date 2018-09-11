package org.messtin.framework.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.messtin.framework.core.loader.BeanLoader;
import org.messtin.framework.core.loader.FieldLoader;
import org.messtin.framework.core.loader.iface.MesstinLoader;
import org.messtin.framework.core.util.ClassUtil;

import java.util.*;

/**
 * Init the whole project.
 *
 * @author majinliang
 */
public final class Init {
    private static final Logger logger = LogManager.getLogger(Init.class);
    /**
     * The loader list.
     * There are sequence among those loaders,
     * {@link BeanLoader} should first load.
     * Then other loaders could load.
     */
    private static final List<Class<? extends MesstinLoader>> loaders =
            new ArrayList<Class<? extends MesstinLoader>>() {{
                add(BeanLoader.class);
                add(FieldLoader.class);
            }};

    public static void init(String... packets) throws Exception {
        logger.info("Initializing messtin core.");
        Set<Class<?>> clazzs = new HashSet<>();
        for (String packet : packets) {
            clazzs.addAll(ClassUtil.getClasses(packet));
        }

        for (Class<? extends MesstinLoader> loader : loaders) {
            MesstinLoader currentLoader = loader.newInstance();
            currentLoader.load(clazzs);
        }
    }
}
