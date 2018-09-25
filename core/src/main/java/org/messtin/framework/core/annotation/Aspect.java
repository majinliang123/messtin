package org.messtin.framework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When a class has this annotation, it means the class is an aspect.
 * This annotation shows what function will be decorated when initialized into
 * {@link org.messtin.framework.core.container.BeanContainer} by
 * {@link org.messtin.framework.core.loader.BeanLoader}.
 *
 * @author majinliang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Aspect {

    /**
     * The class we want to decorate.
     */
    String clazz();

    /**
     * The method we want to decorate, but all the method should be limited by
     * {@link #clazz()}. Means all the method should under the classes in {@link #clazz()}
     * But it could be {@code "*"}, if it is {@code "*"}, it will decorate all the method under the {@link #clazz()}
     */
    String method() default "*";
}
