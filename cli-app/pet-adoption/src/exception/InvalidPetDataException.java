package exception;

public class InvalidPetDataException extends BusinessException{

    public InvalidPetDataException() {
        super(ErrorCode.INVALID_DATA);
    }

    public InvalidPetDataException(String customMessage) {
        super(ErrorCode.INVALID_DATA, customMessage);
    }
}
