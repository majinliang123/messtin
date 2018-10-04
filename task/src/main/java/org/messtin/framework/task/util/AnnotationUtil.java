package org.messtin.framework.task.util;

import org.messtin.framework.task.annotation.CronTask;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * The util for annotation.
 *
 * @author majinliang
 */
public final class AnnotationUtil {

    /**
     * @param method
     * @return if the {@link Method} has {@link CronTask}.
     */
    public static boolean hasCronTaskAnnotation(Method method) {
        return method.isAnnotationPresent(CronTask.class);
    }

    /**
     * @param clazz
     * @return if the {@link Method} in {@link Class} has {@link CronTask}.
     */
    public static boolean hasCronTaskAnnotation(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .anyMatch(method -> method.isAnnotationPresent(CronTask.class));
    }
}
