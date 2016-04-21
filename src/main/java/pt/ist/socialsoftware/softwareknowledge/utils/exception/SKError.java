package pt.ist.socialsoftware.softwareknowledge.utils.exception;

public class SKError {
    public SKErrorType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    private final SKErrorType type;
    private final String value;

    public SKError(SKErrorType type, String value) {
        this.type = type;
        this.value = value;
    }

}
