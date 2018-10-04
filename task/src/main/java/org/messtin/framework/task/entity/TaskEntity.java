package org.messtin.framework.task.entity;

import java.lang.reflect.Method;

/**
 * The {@link TaskEntity} container all information when we run the task.
 *
 * @author majinliang
 */
public class TaskEntity {

    private Object bean;
    private Method method;
    private String expression;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "bean=" + bean +
                ", method=" + method +
                ", expression='" + expression + '\'' +
                '}';
    }
}
