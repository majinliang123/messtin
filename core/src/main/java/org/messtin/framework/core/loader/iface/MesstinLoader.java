package org.messtin.framework.core.loader.iface;

import java.util.Set;

/**
 * Load {@link Class} element to container.
 * 1. class
 * 2. field
 * 3. function
 *
 * @author majinliang
 */
public interface MesstinLoader {

    /**
     * Do load.
     * @param clazzs
     */
    void load(Set<Class<?>> clazzs);
}
