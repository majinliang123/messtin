package org.messtin.framework.core.aspect;

/**
 * The abstract class of aspect.
 * Every aspect should extends this class
 * and add {@link org.messtin.framework.core.annotation.Aspect} annotation.
 *
 * @author majinliang
 */
public abstract class AbstractAspect {

    public void beforeMethod() {

    }

    public void beforeMethodReturn() {

    }

    public Object afterMethod() {
        return null;
    }
}
