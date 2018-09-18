package org.messtin.framework.core.exception;

/**
 * The exception when could get more than one bean from
 * {@link org.messtin.framework.core.container.BeanContainer}
 *
 * @author majinliang
 */
public class BeanConflictException extends CoreException {
    public BeanConflictException(String msg) {
        super(msg);
    }

    public BeanConflictException(Throwable cause) {
        super(cause);
    }
}
