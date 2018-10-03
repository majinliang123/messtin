package org.messtin.framework.web.entity;

import org.messtin.framework.web.adapter.iface.Adapter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * The entity for mvc.
 * It container all info we need when deal with the request.
 *
 * @author majinliang
 */
public class MvcEntity {

    private Method method;
    private Object bean;
    private Adapter adapter;
    private String path;
    private Parameter[] parameters;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "MvcEntity{" +
                "method=" + method +
                ", bean=" + bean +
                ", adapter=" + adapter +
                ", path='" + path + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
