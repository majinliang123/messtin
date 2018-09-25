package org.messtin.framework.core.container;

import org.messtin.framework.core.entity.AspectEntity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The container of interceptor.
 *
 * @author majinliang
 */
public final class InterceptorContainer {

    /**
     * The container of interceptor.
     * It is a map from method to some {@link AspectEntity}.
     * The {@link Method} is what we need intercepted.
     * The {@link AspectEntity} is what we will run for the {@link Method}.
     * <p>
     * method -> some aspect entity
     */
    private static final Map<Method, List<AspectEntity>> CONTAINER = new HashMap<>();

    public static void put(Method method, AspectEntity aspectEntity) {
        List<AspectEntity> aspectEntities;
        if (CONTAINER.get(method) != null) {
            aspectEntities = CONTAINER.get(method);
        } else {
            aspectEntities = new ArrayList<>();
            CONTAINER.put(method, aspectEntities);
        }
        aspectEntities.add(aspectEntity);
    }

    public static List<AspectEntity> get(Method method) {
        return CONTAINER.get(method);
    }
}
