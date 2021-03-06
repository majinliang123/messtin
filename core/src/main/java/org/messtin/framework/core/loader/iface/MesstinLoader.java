package org.messtin.framework.core.loader.iface;

import org.messtin.framework.core.exception.IllegalBeanNameException;

import java.lang.reflect.InvocationTargetException;
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
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IllegalBeanNameException
     */
    void load(Set<Class<?>> clazzs) throws IllegalAccessException, InstantiationException, IllegalBeanNameException, InvocationTargetException;
}
