package org.messtin.framework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When a class has this annotation,
 * will generate a singleton instance and put it into
 * {@link org.messtin.framework.core.container.BeanContainer}.
 * <p>
 * This annotation should put above a class but an abstracted class or interface.
 *
 * @author majinliang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bean {
    String value() default "";
}
