package org.messtin.framework.core.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.messtin.framework.core.aspect.AbstractAspect;
import org.messtin.framework.core.container.AspectContainer;
import org.messtin.framework.core.container.InterceptorContainer;
import org.messtin.framework.core.entity.AspectEntity;
import org.messtin.framework.core.util.AnnotationUtil;
import org.messtin.framework.core.util.AntUtil;
import org.messtin.framework.core.util.ClassUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * The proxy use <a href="https://github.com/cglib/cglib">cglib</a>
 *
 * @author majinliang
 */
public class CGlibProxy implements MethodInterceptor {
    /**
     * Get the proxy of clazz.
     * The core for AOP.
     *
     * @param clazz
     * @return the proxy of clazz.
     */
    public Object getProxy(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        if (ClassUtil.isAbstractClass(clazz)) {
            return null;
        }
        if (ClassUtil.isInterface(clazz)) {
            return null;
        }
        if (!isNeedProxy(clazz)) {
            return clazz.newInstance();
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * Check if the clazz need a proxy.
     * When there are some {@link AspectEntity} in {@link AspectContainer},
     * it means that the clazz need a proxy.
     *
     * @param clazz
     * @return if the clazz need a proxy.
     */
    public boolean isNeedProxy(Class<?> clazz) {
        boolean isNeed = false;
        List<AspectEntity> aspectEntityList = AspectContainer.getAllAspectEntities();
        for (AspectEntity aspectEntity : aspectEntityList) {
            if (!AnnotationUtil.hasBeanAnnotation(clazz)) {
                continue;
            }
            if (!AntUtil.isAntMatch(clazz.getName(), aspectEntity.getClazzPath())) {
                continue;
            }
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!AntUtil.isAntMatch(method.getName(), aspectEntity.getMethodPath())) {
                    continue;
                }
                isNeed = true;
                InterceptorContainer.put(method, aspectEntity);
            }
        }
        return isNeed;
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
        List<AspectEntity> aspectEntities = InterceptorContainer.get(method);
        List<AbstractAspect> abstractAspects = new ArrayList<>();
        for (AspectEntity aspectEntity : aspectEntities) {
            AbstractAspect aspect = (AbstractAspect) aspectEntity.getAspectClass().newInstance();
            abstractAspects.add(aspect);
        }

        for (AbstractAspect aspect : abstractAspects) {
            aspect.beforeMethod();
        }
        Object result = methodProxy.invokeSuper(o, objects);
        for (AbstractAspect aspect : abstractAspects) {
            aspect.beforeMethodReturn();
        }
        for (AbstractAspect aspect : abstractAspects) {
            result = aspect.afterMethod(result);
        }
        return result;
    }
}
