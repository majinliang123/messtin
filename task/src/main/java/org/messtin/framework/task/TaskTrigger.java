package org.messtin.framework.task;

import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.annotation.PostConstruct;
import org.messtin.framework.task.container.TaskContainer;
import org.messtin.framework.task.cron.CronExpression;
import org.messtin.framework.task.entity.TaskEntity;
import org.messtin.framework.task.pool.TaskPool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

/**
 * TaskTrigger scheduled task.
 *
 * @author majinliang
 */
@Bean("TaskTrigger")
public class TaskTrigger {

    @PostConstruct
    public void taskInit() {
        for (TaskEntity entity : TaskContainer.get()) {
            trigger(entity);
        }
    }

    private void trigger(TaskEntity entity) {
        CronExpression cronExp = new CronExpression(entity.getExpression());
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime nextTime = cronExp.nextTimeAfter(zonedDateTime);
        long delay = Date.from(nextTime.toInstant()).getTime() - System.currentTimeMillis();

        TaskPool.schedule(() -> {
            try {
                Method method = entity.getMethod();
                Object bean = entity.getBean();

                method.invoke(bean, new Object[0]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                trigger(entity);
            }
        }, delay, TimeUnit.MILLISECONDS);
    }
}
