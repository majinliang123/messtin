package org.messtin.framework.core.entity;

import org.messtin.framework.core.annotation.Aspect;
import org.messtin.framework.core.util.AnnotationUtil;
import org.messtin.framework.core.util.ClassUtil;

/**
 * The entity of aspect.
 * When there are some class decorated by
 * {@link org.messtin.framework.core.annotation.Aspect},
 * will build the class into {@link AspectEntity}.
 *
 * @author majinliang
 */
public class AspectEntity {

    /**
     * Build a {@link AspectEntity} according to clazz.
     *
     * @param clazz
     * @return
     */
    public static AspectEntity of(Class<?> clazz) {
        if (!AnnotationUtil.hasAspectAnnotation(clazz)) {
            return null;
        }
        if (ClassUtil.isAbstractClass(clazz)) {
            return null;
        }
        if (ClassUtil.isInterface(clazz)) {
            return null;
        }

        Aspect aspectAnnotation = clazz.getDeclaredAnnotation(Aspect.class);
        String clazzPath = aspectAnnotation.clazz();
        String methodPath = aspectAnnotation.method();
        AspectEntity entity = new AspectEntity();
        entity.setClazzPath(clazzPath);
        entity.setMethodPath(methodPath);
        entity.setAspectClass(clazz);
        return entity;
    }


    private String clazzPath;
    private String methodPath;
    private Class<?> aspectClass;

    private AspectEntity() {
    }

    public String getClazzPath() {
        return clazzPath;
    }

    public void setClazzPath(String clazzPath) {
        this.clazzPath = clazzPath;
    }

    public String getMethodPath() {
        return methodPath;
    }

    public void setMethodPath(String methodPath) {
        this.methodPath = methodPath;
    }

    public Class<?> getAspectClass() {
        return aspectClass;
    }

    public void setAspectClass(Class<?> aspectClass) {
        this.aspectClass = aspectClass;
    }
}
