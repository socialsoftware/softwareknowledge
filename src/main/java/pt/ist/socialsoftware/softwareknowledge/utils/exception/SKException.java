package pt.ist.socialsoftware.softwareknowledge.utils.exception;

public class SKException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private SKErrorType error;
    private String message;

    public SKException(SKErrorType error) {
        this.error = error;
    }

    public SKException(SKErrorType error, String message) {
        this.error = error;
        this.message = message;
    }

    public SKErrorType getError() {
        return error;
    }

    public void setError(SKErrorType error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}