package org.messtin.framework.core.util;

import org.messtin.framework.core.annotation.Autowired;
import org.messtin.framework.core.annotation.Bean;

import java.lang.reflect.Field;

/**
 * The util of annotation.
 * Mainly use to check if annotation existed or its value.
 *
 * @author majinliang
 */
public final class AnnotationUtil {

    /**
     * @param clazz
     * @return if the {@link Class} has {@link org.messtin.framework.core.annotation.Bean}
     */
    public static boolean hasBeanAnnotation(Class<?> clazz) {
        return clazz.isAnnotationPresent(Bean.class);
    }

    /**
     * @param field
     * @return if the {@link Field} has {@link org.messtin.framework.core.annotation.Autowired}
     */
    public static boolean hasAutowiredAnnotation(Field field) {
        return field.isAnnotationPresent(Autowired.class);
    }
}
