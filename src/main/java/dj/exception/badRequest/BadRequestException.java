package dj.exception.badRequest;

import dj.exception.ErrorMessage;

public class BadRequestException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BadRequestException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

}
