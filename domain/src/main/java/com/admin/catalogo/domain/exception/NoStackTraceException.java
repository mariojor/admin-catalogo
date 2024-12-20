package com.admin.catalogo.domain.exception;

public class NoStackTraceException extends RuntimeException {
    public NoStackTraceException(final String message) {
        super(message, null);
    }

    public NoStackTraceException(final String message, final Throwable cause) {
        super(message, cause, true, false);
    }
}
