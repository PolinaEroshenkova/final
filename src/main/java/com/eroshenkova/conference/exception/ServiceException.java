package com.eroshenkova.conference.exception;

/**
 * Exception of service layer.
 *
 * @author Palina Yerashenkava
 */
public class ServiceException extends Exception {

    /**
     * Basic constructor of Throwable
     */
    public ServiceException() {
    }

    /**
     * Basic constructor of Throwable
     * @param message is message entered when exception occurred
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Basic constructor of Throwable
     * @param message is message entered when exception occurred
     * @param cause is object that caused exception
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Basic constructor of Throwable
     * @param cause is object that caused exception
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
