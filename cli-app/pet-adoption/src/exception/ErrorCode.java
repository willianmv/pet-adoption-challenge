package exception;

public enum ErrorCode {

    INVALID_DATA("Valor inserido é inválido.");

    final String defaultMessage;

    ErrorCode(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
