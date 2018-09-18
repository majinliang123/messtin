package org.messtin.framework.core.container;


import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.exception.BeanConflictException;
import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * The container of bean.
 * Could put bean into the container or get bean object from the container.
 *
 * @author majinliang
 */
public class BeanContainer {
    /**
     * The container of the bean and its info.
     * bean name -> {bean class name -> bean instance}
     */
    private static volatile Map<String, Map<String, Object>> beanContainer =
            new HashMap<>();

    /**
     * Put the instance of clazz into {@link #beanContainer}.
     * Use the {@link Bean#value()} as bean name.
     * Use the {@link Class#getName()} as its bean class name.
     * Create instance of clazz as its bean instance.
     *
     * @param clazz
     * @throws IllegalBeanNameException the exception when {@link Bean#value()} is null or empty string.
     * @throws IllegalAccessException   the exception when failed create bean instance
     * @throws InstantiationException   the exception when failed create bean instance
     */
    public static void put(Class<?> clazz) throws IllegalBeanNameException, IllegalAccessException, InstantiationException {
        Bean beanAnnotation = clazz.getDeclaredAnnotation(Bean.class);
        if (StringUtil.isNullOrEmpty(beanAnnotation)) {
            return;
        }
        String beanName = beanAnnotation.value();
        if (StringUtil.isNullOrEmpty(beanName)) {
            throw new IllegalBeanNameException("The bean name is illegal. It is null or empty");
        }
        String clazzName = clazz.getName();
        Map<String, Object> beanMap = beanContainer.get(beanName);
        if (beanMap == null) {
            beanMap = new HashMap<>();
        }
        beanMap.put(clazzName, clazz.newInstance());
        beanContainer.put(beanName, beanMap);
    }

    /**
     * Get bean object from container according to clazz.
     *
     * @param clazz the clazz of the bean
     * @return the object we found from bean container.
     * @throws IllegalBeanNameException
     */
    public static Object get(Class<?> clazz) throws IllegalBeanNameException {
        Bean beanAnnotation = clazz.getDeclaredAnnotation(Bean.class);
        if (StringUtil.isNullOrEmpty(beanAnnotation)) {
            return null;
        }
        String beanName = beanAnnotation.value();
        if (StringUtil.isNullOrEmpty(beanName)) {
            throw new IllegalBeanNameException("The bean name is illegal. It is null or empty");
        }
        Map<String, Object> beanMap = beanContainer.get(beanName);
        if (StringUtil.isNullOrEmpty(beanName)) {
            return null;
        }
        String clazzName = clazz.getName();
        return beanMap.get(clazzName);
    }

    /**
     * Get bean object from container according to bean name.
     *
     * @param beanName the bean name of the bean in {@link #beanContainer}
     * @return the object we found from bean container.
     * @throws IllegalBeanNameException the exception when the bean name is null or empty string.
     * @throws BeanConflictException    the exception when we get more than one bean object from container.
     */
    public static Object get(String beanName) throws IllegalBeanNameException, BeanConflictException {
        if (StringUtil.isNullOrEmpty(beanName)) {
            throw new IllegalBeanNameException("The bean name is illegal. It is null or empty");
        }
        Map<String, Object> beanMap = beanContainer.get(beanName);
        if (beanMap == null || beanMap.isEmpty()) {
            return null;
        }
        if (beanMap.size() > 1) {
            throw new BeanConflictException("There are " + beanMap.size() + " in the bean container.");
        }
        return beanMap.values().iterator().next();
    }
}
