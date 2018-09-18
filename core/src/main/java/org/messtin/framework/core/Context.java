package org.messtin.framework.core;

import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.exception.BeanConflictException;
import org.messtin.framework.core.exception.IllegalBeanNameException;

/**
 * The interface for the user app use to get bean or other information.
 * After user initialized messtin core, it will return a object of {@link Context}.
 * It will only be get after initialized.
 *
 * @author majinliang
 */
public final class Context {
    Context(){}
    public Object getBean(String beanName) throws BeanConflictException, IllegalBeanNameException {
        return BeanContainer.get(beanName);
    }

    public Object getBean(Class<?> clazz) throws BeanConflictException, IllegalBeanNameException {
        return BeanContainer.get(clazz);
    }
}
