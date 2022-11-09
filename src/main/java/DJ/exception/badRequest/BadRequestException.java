package DJ.exception.badRequest;

import DJ.exception.ErrorMessage;

public class BadRequestException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BadRequestException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

}
