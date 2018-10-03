package org.messtin.framework.core.container;


import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.exception.BeanConflictException;
import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.proxy.CGlibProxy;
import org.messtin.framework.core.util.ClassUtil;
import org.messtin.framework.core.util.StringUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The container of bean.
 * Could put bean into the container or get bean object from the container.
 *
 * @author majinliang
 */
public final class BeanContainer {
    /**
     * The container of the bean and its info.
     * bean name -> {bean class name -> bean instance}
     */
    private static volatile Map<String, Map<String, Object>> beanContainer =
            new HashMap<>();

    /**
     * Put the instance into {@link #beanContainer}
     *
     * @param beanName
     * @param clazzName
     * @param instance
     */
    public static void put(String beanName, String clazzName, Object instance) {
        Map<String, Object> beanMap = beanContainer.get(beanName);
        if (beanMap == null) {
            beanMap = new HashMap<>();
        }
        beanMap.put(clazzName, instance);
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

    /**
     * Get bean object from container according to bean name and clazz.
     *
     * @param beanName the bean name of the bean in {@link #beanContainer}
     * @return the object we found from bean container.
     * @throws IllegalBeanNameException the exception when the bean name is null or empty string.
     * @throws BeanConflictException    the exception when we get more than one bean object from container.
     */
    public static Object get(String beanName, Class<?> clazz) throws IllegalBeanNameException {
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
     * Get all bean in {@link #beanContainer}
     *
     * @return list of bean object.
     */
    public static List<Object> getBeans() {
        return beanContainer.values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
