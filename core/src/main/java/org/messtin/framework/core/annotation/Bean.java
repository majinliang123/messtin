package org.messtin.framework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1. When a class has this annotation,
 * will generate a singleton instance and put it into
 * {@link org.messtin.framework.core.container.BeanContainer}.
 *
 * 2. When a method has this annotation,
 * will run the method and will cache the result.
 * So when the method is called again,
 * will not run the method again,
 * will get the result cached before.
 * <p>
 * This annotation should put above a class but an abstracted class or interface.
 *
 * @author majinliang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Bean {
    String value() default "";
}
