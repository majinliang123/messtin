package org.messtin.framework.core.annotation;

import org.messtin.framework.core.entity.AspectEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation should decorate some functions, and this function's class is decorated by
 * {@link org.messtin.framework.core.annotation.Aspect}.
 *
 * This annotation shows what function will be decorated when initialized into
 * {@link org.messtin.framework.core.container.BeanContainer} by
 * {@link org.messtin.framework.core.loader.BeanLoader}.
 *
 * @author majinliang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PointCut {

    /**
     * The class we want to decorate.
     */
    Class<?>[] clazz();

    /**
     * The methods we want to decorate, but all the methods should be limited by
     * {@link #clazz()}. Means all the methods should under the classes in {@link #clazz()}
     * But it could be empty, if it is empty, it will decorate all the methods under the {@link #clazz()}
     */
    String[] methods() default {};

    /**
     * The location where we should run the aspect.
     * We could run it before the function or before the function return or other cases.
     */
    AspectEntity.LOCATION location();
}
