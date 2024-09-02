package com.challenge.customer.exception;

import lombok.Getter;

public class UseCaseException extends RuntimeException{

    private static final long serialVertionUID = 1L;

    @Getter
    private final ExceptionType exceptionType;

    /**
     * Exception for usecases.
     *
     * @param message of exception.
     * @param exceptionType is a type.
     */
    public UseCaseException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    /**
     * Exception for usecases.
     *
     * @param message of exception.
     * @param cause origin exception.
     * @param exceptionType is a type.
     */
    public UseCaseException(String message, Throwable cause, ExceptionType exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }

    /**
     * Exception for usecases.
     *
     * @param message of exception.
     * @param cause origin exception.
     */
    public UseCaseException(String message, Throwable cause) {
        this(message, cause, ExceptionType.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception for usecases.
     *
     * @param cause origin exception.
     * @param exceptionType exception type.
     */
    public UseCaseException(Throwable cause, ExceptionType exceptionType) {
        this(cause.getMessage(), cause, ExceptionType.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception for usecases.
     *
     * @param cause of exception.
     */
    public UseCaseException(Throwable cause) {this(cause.getMessage(), cause);}

    public UseCaseException(String message) {
        super(message);
        this.exceptionType = ExceptionType.INTERNAL_SERVER_ERROR;
    }
}
