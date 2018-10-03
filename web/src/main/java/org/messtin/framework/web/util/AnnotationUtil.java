package org.messtin.framework.web.util;

import org.messtin.framework.web.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * The util for annotation in web.
 *
 * @author majinliang
 */
public final class AnnotationUtil {

    /**
     * @param clazz
     * @return if the clazz has {@link RequestMapping}.
     */
    public static boolean hasRequestMappingAnnotation(Class<?> clazz) {
        return clazz.isAnnotationPresent(RequestMapping.class);
    }

    /**
     * @param method
     * @return if the method has {@link RequestMapping}
     */
    public static boolean hasRequestMappingAnnotation(Method method){
        return method.isAnnotationPresent(RequestMapping.class);
    }
}
