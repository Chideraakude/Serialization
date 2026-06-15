package techTribe.exception;

public class JsonToTransactionDeserializationFailedException extends RuntimeException {
    public JsonToTransactionDeserializationFailedException(String message) {
        super(message);
    }
}
