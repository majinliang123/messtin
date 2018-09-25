package org.messtin.framework.core.container;

import org.messtin.framework.core.entity.AspectEntity;
import org.messtin.framework.core.util.AnnotationUtil;
import org.messtin.framework.core.util.ClassUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * The container of {@link AspectEntity}.
 *
 * @author majinliang
 */
public final class AspectContainer {

    /**
     * The container of {@link AspectEntity}.
     */
    private static final List<AspectEntity> CONTAINER = new ArrayList<>();

    /**
     * Put the {@link AspectEntity} into container.
     * 1. Build class into AspectEntity list.
     * 2. Put the AspectEntity into container.
     *
     * @param clazz the class we will put into the container.
     */
    public static void put(Class<?> clazz) {
        if (!AnnotationUtil.hasAspectAnnotation(clazz)) {
            return;
        }
        if (ClassUtil.isAbstractClass(clazz)) {
            return;
        }
        if (ClassUtil.isInterface(clazz)) {
            return;
        }
        AspectEntity entity = AspectEntity.of(clazz);
        CONTAINER.add(entity);
    }

    public static List<AspectEntity> getAllAspectEntities() {
        return CONTAINER;
    }
}
