package org.messtin.framework.core.container;


import java.util.HashMap;
import java.util.Map;

/**
 * @author majinliang
 */
public class BeanContainer {
    /**
     * The container of the bean and its info.
     * bean name -> {bean class -> bean instance}
     */
    private static volatile Map<String, Map<Class<?>, Object>> beanContainer =
            new HashMap<>();

    public static void put(Class<?> clazz) {

    }
}
