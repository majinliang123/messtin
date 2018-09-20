package org.messtin.framework.core.entity;

import org.messtin.framework.core.annotation.PointCut;
import org.messtin.framework.core.util.AnnotationUtil;
import org.messtin.framework.core.util.ClassUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The entity of aspect.
 * When there are some class decorated by
 * {@link org.messtin.framework.core.annotation.Aspect}, will build the class into {@link AspectEntity}.
 *
 * @author majinliang
 */
public class AspectEntity {

    /**
     * Where will run the aspect.
     * BEFORE: before the function run.
     * BEFORE_RETURN: after the function run but before the return run.
     */
    public static enum LOCATION {
        BEFORE,
        BEFORE_RETURN
    }

    public static List<AspectEntity> of(Class<?> clazz) {
        if (!AnnotationUtil.hasAspectAnnotation(clazz)) {
            return null;
        }
        if (ClassUtil.isAbstractClass(clazz)) {
            return null;
        }
        if (ClassUtil.isInterface(clazz)) {
            return null;
        }

        List<AspectEntity> aspectEntities = new ArrayList<>();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (!AnnotationUtil.hasPointCutAnnotation(method)) {
                continue;
            }
            AspectEntity aspectEntity = new AspectEntity();
            PointCut pointCut = method.getAnnotation(PointCut.class);
            aspectEntity.setClazzs(Arrays.asList(pointCut.clazz()));
            aspectEntity.setMethods(Arrays.asList(pointCut.methods()));
            aspectEntity.setLocation(pointCut.location());
            aspectEntity.setAspectMethod(method);
            aspectEntity.setAspectClass(clazz);
        }
        return aspectEntities;
    }


    private List<Class<?>> clazzs;
    private List<String> methods;
    private LOCATION location;
    private Method aspectMethod;
    private Class<?> aspectClass;

    private AspectEntity() {
    }

    public List<Class<?>> getClazzs() {
        return clazzs;
    }

    public void setClazzs(List<Class<?>> clazzs) {
        this.clazzs = clazzs;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public LOCATION getLocation() {
        return location;
    }

    public void setLocation(LOCATION location) {
        this.location = location;
    }

    public Method getAspectMethod() {
        return aspectMethod;
    }

    public void setAspectMethod(Method aspectMethod) {
        this.aspectMethod = aspectMethod;
    }

    public Class<?> getAspectClass() {
        return aspectClass;
    }

    public void setAspectClass(Class<?> aspectClass) {
        this.aspectClass = aspectClass;
    }
}
