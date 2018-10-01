package org.messtin.framework.core.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.messtin.framework.core.util.ClassUtil;

public abstract class AbstractProxy implements MethodInterceptor {
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

    public abstract boolean isNeedProxy(Class<?> clazz);
}
