package org.messtin.framework.core.loader;

import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.loader.iface.MesstinLoader;
import org.messtin.framework.core.util.AnnotationUtil;
import org.messtin.framework.core.util.ClassUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Use to run post construct function which has
 * {@link org.messtin.framework.core.annotation.PostConstruct}
 * after bean is created.
 *
 * @author majinliang
 */
public class PostConstructLoader implements MesstinLoader {
    @Override
    public void load(Set<Class<?>> clazzs) throws IllegalBeanNameException, IllegalAccessException, InvocationTargetException {
        for (Class<?> clazz : clazzs) {
            if (ClassUtil.isInterface(clazz)) {
                continue;
            }
            if (ClassUtil.isAbstractClass(clazz)) {
                continue;
            }
            if (!AnnotationUtil.hasBeanAnnotation(clazz)) {
                continue;
            }
            if (!AnnotationUtil.hasPostConstructAnnotation(clazz)) {
                continue;
            }

            Bean beanAnno = clazz.getAnnotation(Bean.class);
            String beanName = beanAnno.value();
            Object bean = BeanContainer.get(beanName, clazz);

            for (Method method : clazz.getDeclaredMethods()) {
                if (!AnnotationUtil.hasPostConstructAnnotation(method)) {
                    continue;
                }
                method.invoke(bean, new Object[0]);
            }
        }
    }
}
