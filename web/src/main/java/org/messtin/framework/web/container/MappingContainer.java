package org.messtin.framework.web.container;

import org.messtin.framework.web.entity.MvcEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * The container for map of request.
 *
 * @author majinliang
 */
public final class MappingContainer {
    private static final Map<String, MvcEntity> MAPPING_CONTAINER = new HashMap<>();

    public static void put(String path, MvcEntity entity) {
        MAPPING_CONTAINER.put(path, entity);
    }

    public static void add(MvcEntity entity){
        MAPPING_CONTAINER.put(entity.getPath(), entity);
    }

    public static MvcEntity get(String path) {
        return MAPPING_CONTAINER.get(path);
    }
}
