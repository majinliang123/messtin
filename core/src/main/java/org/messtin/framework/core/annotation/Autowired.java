package org.messtin.framework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When a field has this annotation, it will set corresponding value
 * if it existed at {@link org.messtin.framework.core.container.BeanContainer}.
 * The class of this field <strong>must</strong> have annotation {@link Bean}
 * <p>
 * In the future, it will support inject value into function
 *
 * @author majinliang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {
    String value() default "";
}
