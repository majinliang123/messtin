package org.messtin.framework.core.util;

import org.messtin.framework.core.annotation.Aspect;
import org.messtin.framework.core.annotation.Autowired;
import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.annotation.PostConstruct;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * The util of annotation.
 * Mainly use to check if annotation existed or its value.
 *
 * @author majinliang
 */
public final class AnnotationUtil {

    /**
     * @param clazz
     * @return if the {@link Class} has {@link Bean} annotation.
     */
    public static boolean hasBeanAnnotation(Class<?> clazz) {
        return clazz.isAnnotationPresent(Bean.class);
    }

    /**
     * @param method
     * @return if the {@link Method} has {@link Bean} annotation.
     */
    public static boolean hasBeanAnnotation(Method method) {
        return method.isAnnotationPresent(Bean.class);
    }

    /**
     * @param parameter
     * @return if the {@link Parameter} has {@link Bean} annotation.
     */
    public static boolean hasBeanAnnotation(Parameter parameter) {
        return parameter.isAnnotationPresent(Bean.class);
    }

    /**
     * @param field
     * @return if the {@link Field} has {@link Autowired} annotation.
     */
    public static boolean hasAutowiredAnnotation(Field field) {
        return field.isAnnotationPresent(Autowired.class);
    }

    /**
     * @param clazz
     * @return if the {@link Class} has {@link Aspect} annotation.
     */
    public static boolean hasAspectAnnotation(Class<?> clazz) {
        return clazz.isAnnotationPresent(Aspect.class);
    }

    /**
     * @param clazz
     * @return if the {@link Method} in {@link Class} has {@link PostConstruct} annotation.
     * And the {@link Method} need has no parameter.
     */
    public static boolean hasPostConstructAnnotation(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .anyMatch(method ->
                        method.isAnnotationPresent(PostConstruct.class) &&
                                method.getParameterCount() == 0);
    }

    /**
     * @param method
     * @return if the {@link Method} has {@link PostConstruct} annotation.
     * And the {@link Method} need has no parameter.
     */
    public static boolean hasPostConstructAnnotation(Method method) {
        return method.isAnnotationPresent(PostConstruct.class) &&
                method.getParameterCount() == 0;
    }

    /**
     * @param clazz
     * @return if the class have bean method.
     */
    public static boolean hasBeanMethod(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        return Arrays.stream(methods)
                .anyMatch(AnnotationUtil::hasBeanAnnotation);
    }
}
