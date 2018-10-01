package org.messtin.framework.core.loader;

import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.loader.iface.MesstinLoader;
import org.messtin.framework.core.proxy.AbstractProxy;
import org.messtin.framework.core.proxy.MethodBeanProxy;
import org.messtin.framework.core.util.AnnotationUtil;
import org.messtin.framework.core.util.ClassUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Run method when a method have {@link org.messtin.framework.core.annotation.Bean}.
 * After run, will put the returned value into
 * {@link org.messtin.framework.core.container.BeanContainer}.
 *
 * @author majinliang
 */
public class MethodLoader implements MesstinLoader {

    @Override
    public void load(Set<Class<?>> clazzs) throws IllegalBeanNameException, InvocationTargetException, IllegalAccessException, InstantiationException {

        for (Class<?> clazz : clazzs) {
            if (!AnnotationUtil.hasBeanAnnotation(clazz)) {
                continue;
            }
            if (ClassUtil.isAbstractClass(clazz)) {
                continue;
            }
            if (ClassUtil.isInterface(clazz)) {
                continue;
            }

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!AnnotationUtil.hasBeanAnnotation(method)) {
                    continue;
                }
                if (!ClassUtil.hasReturnValue(method)) {
                    continue;
                }
                if (method.getParameterCount() != 0) {
                    continue;
                }
                Object bean = BeanContainer.get(clazz);
                Class<?> returnType = method.getReturnType();
                Bean annotation = method.getAnnotation(Bean.class);
                String beanName = annotation.value();
                String clazzName = returnType.getName();
                Object result = method.invoke(bean, new Object[]{});
                BeanContainer.put(beanName, clazzName, result);
            }

            AbstractProxy proxy = new MethodBeanProxy();
            Object instance = proxy.getProxy(clazz);
            Bean annotation = clazz.getAnnotation(Bean.class);
            String beanName = annotation.value();
            String clazzName = clazz.getName();
            BeanContainer.put(beanName, clazzName, instance);
        }
    }
}
