package org.messtin.framework.core.loader;

import org.messtin.framework.core.loader.iface.MesstinLoader;

import java.util.Set;

/**
 * The loader uses to set value to object field which has {@link org.messtin.framework.core.annotation.Autowired}.
 * But the class which contains this field <strong>must</strong> is a bean.
 * The final result will update object at {@link org.messtin.framework.core.container.BeanContainer}
 * If the class doesn't have {@link org.messtin.framework.core.annotation.Bean},
 * it will not be processed.
 *
 * @author majinliang
 */
public class FieldLoader implements MesstinLoader {
    @Override
    public void load(Set<Class<?>> clazzs) {

    }
}
