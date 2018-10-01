package org.messtin.framework.core.loader;

import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.loader.iface.MesstinLoader;
import org.messtin.framework.core.proxy.CGlibProxy;
import org.messtin.framework.core.util.AnnotationUtil;
import org.messtin.framework.core.util.ClassUtil;
import org.messtin.framework.core.util.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The loader uses to load bean class
 * {@link org.messtin.framework.core.container.BeanContainer}
 *
 * @author majinliang
 */
public class BeanLoader implements MesstinLoader {

    /**
     * Put the instance of clazz into {@link BeanContainer}.
     * Use the {@link Bean#value()} as bean name.
     * Use the {@link Class#getName()} as its bean class name.
     * Create instance of clazz as its bean instance.
     *
     * @param clazzs
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IllegalBeanNameException
     */
    @Override
    public void load(Set<Class<?>> clazzs) throws IllegalAccessException, InstantiationException, IllegalBeanNameException {

        for (Class<?> clazz : clazzs) {
            Bean beanAnnotation = clazz.getDeclaredAnnotation(Bean.class);
            if (StringUtil.isNullOrEmpty(beanAnnotation)) {
                continue;
            }
            if (ClassUtil.isAbstractClass(clazz)) {
                continue;
            }
            if (ClassUtil.isInterface(clazz)) {
                continue;
            }
            String beanName = beanAnnotation.value();
            if (StringUtil.isNullOrEmpty(beanName)) {
                throw new IllegalBeanNameException("The bean name is illegal. It is null or empty");
            }
            String clazzName = clazz.getName();
            CGlibProxy proxy = new CGlibProxy();
            Object instance = proxy.getProxy(clazz);
            BeanContainer.put(beanName, clazzName, instance);
        }
    }
}
