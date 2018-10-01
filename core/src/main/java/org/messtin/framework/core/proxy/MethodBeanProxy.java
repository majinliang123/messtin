package org.messtin.framework.core.proxy;

import net.sf.cglib.proxy.MethodProxy;
import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.util.AnnotationUtil;
import org.messtin.framework.core.util.ClassUtil;

import java.lang.reflect.Method;

/**
 * Use to method which has bean.
 *
 * @author majinliang
 */
public class MethodBeanProxy extends AbstractProxy {
    @Override
    public boolean isNeedProxy(Class<?> clazz) {
        if (!AnnotationUtil.hasBeanAnnotation(clazz)) {
            return false;
        }
        if (ClassUtil.isAbstractClass(clazz)) {
            return false;
        }
        if (ClassUtil.isInterface(clazz)) {
            return false;
        }
        if(!AnnotationUtil.hasBeanMethod(clazz)){
            return false;
        }
        return true;
    }


    /**
     * Proxy intercept method.
     * When the clazz method is called,
     * {@link #intercept(Object, Method, Object[], MethodProxy)} will be called.
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (AnnotationUtil.hasBeanAnnotation(method)){
            Bean annotation = method.getAnnotation(Bean.class);
            String beanName = annotation.value();
            Class<?> returnType = method.getReturnType();
            return BeanContainer.get(beanName, returnType);
        } else {
            return methodProxy.invokeSuper(o, objects);
        }

    }
}
