package org.messtin.framework.core.exception;

/**
 * The exception when initialize failed.
 * It will happen in below cases:
 * 1. the project has been initialized and it initialized again
 *
 * @author majinliang
 */
public class CoreInitException extends CoreException {
    public CoreInitException(String msg) {
        super(msg);
    }

    public CoreInitException(Throwable cause) {
        super(cause);
    }
}
