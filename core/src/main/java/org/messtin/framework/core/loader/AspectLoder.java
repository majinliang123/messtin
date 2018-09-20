package org.messtin.framework.core.loader;

import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.loader.iface.MesstinLoader;

import java.util.Set;

/**
 * The loader of {@link org.messtin.framework.core.entity.AspectEntity}.
 *
 * @author majinliang
 */
public class AspectLoder implements MesstinLoader {
    @Override
    public void load(Set<Class<?>> clazzs) throws IllegalAccessException, InstantiationException, IllegalBeanNameException {
        for (Class<?> clazz: clazzs){


        }
    }
}
