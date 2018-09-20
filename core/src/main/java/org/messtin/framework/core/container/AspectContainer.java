package org.messtin.framework.core.container;

import org.messtin.framework.core.entity.AspectEntity;
import org.messtin.framework.core.util.AnnotationUtil;
import org.messtin.framework.core.util.ClassUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The container of {@link AspectEntity}.
 *
 * @author majinliang
 */
public final class AspectContainer {

    /**
     * The container of {@link AspectEntity}.
     * method path -> aspect entity list.
     * One method could have one or more {@link AspectEntity}.
     */
    private static final Map<String, List<AspectEntity>> aspectContainer = new HashMap<>();

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
        List<AspectEntity> aspectEntities = AspectEntity.of(clazz);
        for (AspectEntity aspectEntity : aspectEntities) {
            String method = "";
            if (aspectContainer.containsKey(method)) {
                aspectEntities = aspectContainer.get(method);
            } else {
                aspectEntities = new ArrayList<>();
                aspectContainer.put(method, aspectEntities);
            }
            aspectEntities.add(aspectEntity);
        }


    }

    public static List<AspectEntity> get(String method) {
        return aspectContainer.get(method);
    }
}
