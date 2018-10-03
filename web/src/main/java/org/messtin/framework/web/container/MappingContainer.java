package org.messtin.framework.web.container;

import org.messtin.framework.web.entity.MvcEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * The container for map of path and {@link MvcEntity}.
 * The path is the request path.
 * The {@link MvcEntity} has all information which we need to use when deal with the request.
 *
 * @author majinliang
 */
public final class MappingContainer {
    /**
     * path -> {@link MvcEntity}
     */
    private static final Map<String, MvcEntity> MAPPING_CONTAINER = new HashMap<>();

    public static void put(String path, MvcEntity entity) {
        MAPPING_CONTAINER.put(path, entity);
    }

    public static void add(MvcEntity entity) {
        MAPPING_CONTAINER.put(entity.getPath(), entity);
    }

    public static MvcEntity get(String path) {
        return MAPPING_CONTAINER.get(path);
    }
}
