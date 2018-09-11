package org.messtin.framework.core.loader;

import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.loader.iface.MesstinLoader;
import org.messtin.framework.core.util.AnnotationUtil;

import java.util.Set;

/**
 * The loader uses to load bean class
 * {@link org.messtin.framework.core.container.BeanContainer}
 *
 * @author majinliang
 */
public class BeanLoader implements MesstinLoader {

    @Override
    public void load(Set<Class<?>> clazzs){
        clazzs.stream()
                .filter(AnnotationUtil::hasBeanAnnotation)
                .forEach(BeanContainer::put);
    }
}
