package com.challenge.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ExceptionType {
    BAD_REQUEST(HttpStatus.BAD_REQUEST),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;

    /**
     * Retrieve an {@link ExceptionType} from a status code.
     *
     * @param statusCode an integer value representing a status code.
     * @return the matching {@link ExceptionType} or {@link ExceptionType#INTERNAL_SERVER_ERROR} if
     * none is found.
     */
    public static ExceptionType valueOf(int statusCode) {
        return Arrays.stream(values())
                .filter(exceptionType -> exceptionType.status.value() == statusCode)
                .findFirst()
                .orElse(ExceptionType.INTERNAL_SERVER_ERROR);
    }
}
