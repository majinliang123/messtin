package org.messtin.framework.task.container;

import org.messtin.framework.task.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * The container of {@link TaskEntity}
 *
 * @author majinliang
 */
public final class TaskContainer {

    private static final List<TaskEntity> TASK_ENTITY_LIST = new ArrayList<>();

    public static void add(TaskEntity task) {
        TASK_ENTITY_LIST.add(task);
    }

    public static List<TaskEntity> get() {
        return TASK_ENTITY_LIST;
    }
}
