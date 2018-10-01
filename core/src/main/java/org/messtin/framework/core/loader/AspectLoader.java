package org.messtin.framework.core.loader;

import org.messtin.framework.core.container.AspectContainer;
import org.messtin.framework.core.loader.iface.MesstinLoader;

import java.util.Set;

/**
 * The loader of {@link org.messtin.framework.core.entity.AspectEntity}.
 *
 * @author majinliang
 */
public class AspectLoader implements MesstinLoader {

    @Override
    public void load(Set<Class<?>> clazzs) {
        for (Class<?> clazz : clazzs) {
            AspectContainer.put(clazz);
        }
    }
}
