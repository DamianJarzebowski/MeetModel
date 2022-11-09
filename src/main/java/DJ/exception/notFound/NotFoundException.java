package DJ.exception.notFound;


import DJ.exception.ErrorMessage;

public class NotFoundException extends RuntimeException {

    private ErrorMessage errorMessage;

    public NotFoundException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

}
