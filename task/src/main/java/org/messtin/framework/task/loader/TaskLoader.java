package org.messtin.framework.task.loader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.loader.iface.MesstinLoader;
import org.messtin.framework.core.util.ClassUtil;
import org.messtin.framework.task.annotation.CronTask;
import org.messtin.framework.task.container.TaskContainer;
import org.messtin.framework.task.entity.TaskEntity;
import org.messtin.framework.task.util.AnnotationUtil;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Build task into {@link TaskEntity}
 * and then push the {@link TaskEntity} into {@link TaskContainer}.
 *
 * @author majinliang
 */
public class TaskLoader implements MesstinLoader {
    private static final Logger logger = LogManager.getLogger(TaskLoader.class);

    @Override
    public void load(Set<Class<?>> clazzs) throws IllegalBeanNameException {

        for (Class<?> clazz : clazzs) {
            if (ClassUtil.isInterface(clazz)) {
                continue;
            }
            if (ClassUtil.isAbstractClass(clazz)) {
                continue;
            }
            if (!org.messtin.framework.core.util.AnnotationUtil.hasBeanAnnotation(clazz)) {
                continue;
            }
            if (!AnnotationUtil.hasCronTaskAnnotation(clazz)) {
                continue;
            }
            Bean beanAnno = clazz.getAnnotation(Bean.class);
            String beanName = beanAnno.value();
            Object bean = BeanContainer.get(beanName, clazz);

            for (Method method : clazz.getDeclaredMethods()) {
                if (!AnnotationUtil.hasCronTaskAnnotation(method)) {
                    continue;
                }
                CronTask cronTaskAnno = method.getAnnotation(CronTask.class);
                String expression = cronTaskAnno.value();

                TaskEntity task = new TaskEntity();
                task.setBean(bean);
                task.setMethod(method);
                task.setExpression(expression);

                logger.info("load {} >> TaskContainer", task.toString());
                TaskContainer.add(task);
            }
        }
    }
}
