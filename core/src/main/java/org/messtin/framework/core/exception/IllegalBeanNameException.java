package org.messtin.framework.core.exception;

/**
 * The exception when use the illegal bean name.
 *
 * @author majinliang
 */
public class IllegalBeanNameException extends CoreException {
    public IllegalBeanNameException(String msg) {
        super(msg);
    }

    public IllegalBeanNameException(Throwable cause) {
        super(cause);
    }
}
