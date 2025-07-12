package ru.kernogo.gregtech6port.utils.exception;

/**
 * Exception that {@link ru.kernogo.gregtech6port.utils.GTUtils#assureNotNull} throws if an unexpected null value was encountered
 */
public final class GTUnexpectedNullException extends RuntimeException {
    public GTUnexpectedNullException(String message) {
        super(message);
    }

    public GTUnexpectedNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public GTUnexpectedNullException(Throwable cause) {
        super(cause);
    }

    public GTUnexpectedNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
