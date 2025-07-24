package ru.kernogo.gregtech6port.utils.exception;

/**
 * Exception to throw if some validation failed. <br>
 * Only throw if the validation failed unexpectedly. Don't throw for checks that are expected to fail under normal conditions. <br>
 * TODO: static analysis to ensure that this is caught (checked exceptions are annoying)
 */
public final class GTUnexpectedValidationFailException extends RuntimeException {
    public GTUnexpectedValidationFailException(String message) {
        super(message);
    }

    public GTUnexpectedValidationFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public GTUnexpectedValidationFailException(Throwable cause) {
        super(cause);
    }

    public GTUnexpectedValidationFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
