package org.messtin.framework.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.messtin.framework.core.loader.AspectLoader;
import org.messtin.framework.core.loader.BeanLoader;
import org.messtin.framework.core.loader.FieldLoader;
import org.messtin.framework.core.loader.MethodLoader;
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
     * The loader map.
     * There are sequence among those loaderMap,
     * 1. {@link AspectLoader}.
     * 2. {@link BeanLoader}.
     * 3. {@link MethodLoader}.
     * 4. {@link FieldLoader}.
     * ..
     * Then other loaderMap could load.
     */
    private static final Map<Integer, List<Class<? extends MesstinLoader>>> loaderMap =
            new TreeMap<Integer, List<Class<? extends MesstinLoader>>>() {{
                put(0, new ArrayList<Class<? extends MesstinLoader>>() {{
                    add(AspectLoader.class);
                }});
                put(1, new ArrayList<Class<? extends MesstinLoader>>() {{
                    add(BeanLoader.class);
                }});
                put(2, new ArrayList<Class<? extends MesstinLoader>>() {{
                    add(MethodLoader.class);
                }});
                put(3, new ArrayList<Class<? extends MesstinLoader>>() {{
                    add(FieldLoader.class);
                }});
            }};

    /**
     * Add loader to class loader map.
     * So that when init classes, will load the classes by the loader.
     * Use default index as {@link Integer#MAX_VALUE}.
     *
     * @param loader
     */
    public static void addLoader(Class<? extends MesstinLoader> loader) {
        addLoader(Integer.MAX_VALUE, loader);
    }

    /**
     * Add loader to class loader map according to index.
     *
     * @param index
     * @param loader
     */
    public static void addLoader(Integer index, Class<? extends MesstinLoader> loader) {
        List<Class<? extends MesstinLoader>> l = loaderMap.get(index);
        if (l == null) {
            l = new ArrayList<>();
        }
        l.add(loader);
        loaderMap.put(index, l);
    }

    /**
     * Core function to init the whole project.
     *
     * @param packets the packages we need scan.
     * @throws Exception
     */
    public static void init(String... packets) throws Exception {
        logger.info("Initializing messtin core.");
        Set<Class<?>> clazzs = new HashSet<>();
        for (String packet : packets) {
            clazzs.addAll(ClassUtil.getClasses(packet));
        }

        List<Class<? extends MesstinLoader>> loaders = new ArrayList<>();
        for (List<Class<? extends MesstinLoader>> l : loaderMap.values()) {
            loaders.addAll(l);
        }
        for (Class<? extends MesstinLoader> loader : loaders) {
            MesstinLoader currentLoader = loader.newInstance();
            currentLoader.load(clazzs);
        }
    }
}
