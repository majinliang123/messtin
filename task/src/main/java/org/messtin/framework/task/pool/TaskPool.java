package org.messtin.framework.task.pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create a new thread to do the task.
 *
 * @author majinliang
 */
public final class TaskPool {

    private static ScheduledThreadPoolExecutor pool =
            new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());

    public static void schedule(Runnable command, long delay, TimeUnit timeUnit) {
        pool.schedule(command, delay, timeUnit);
    }

}
