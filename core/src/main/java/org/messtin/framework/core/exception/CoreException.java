package org.messtin.framework.core.exception;

/**
 * The highest exception of the project.
 * All other exception will extends it.
 *
 * @author majinliang
 */
public class CoreException extends Exception {
    public CoreException(String msg) {
        super(msg);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }
}
