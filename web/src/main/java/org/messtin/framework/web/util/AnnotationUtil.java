package org.messtin.framework.web.util;

import org.messtin.framework.web.annotation.ParamName;
import org.messtin.framework.web.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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
    public static boolean hasRequestMappingAnnotation(Method method) {
        return method.isAnnotationPresent(RequestMapping.class);
    }

    /**
     * @param param
     * @return if the param has {@link ParamName}.
     */
    public static boolean hasParamNameAnnotation(Parameter param) {
        return param.isAnnotationPresent(ParamName.class);
    }
}
