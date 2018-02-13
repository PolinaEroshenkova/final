package com.eroshenkova.conference.exception;

/**
 * Exception of database layer.
 *
 * @author Palina Yerashenkava
 */
public class DAOException extends Exception {

    /**
     * Basic constructor of Throwable
     */
    public DAOException() {
    }

    /**
     * Basic constructor of Throwable
     * @param message is message entered when exception occurred
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Basic constructor of Throwable
     * @param message is message entered when exception occurred
     * @param cause is object that caused exception
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Basic constructor of Throwable
     * @param cause is object that caused exception
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}
