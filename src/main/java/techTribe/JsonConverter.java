package techTribe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import techTribe.data.Transaction;
import techTribe.exception.TransactionSerializationException;

public class JsonConverter {
    public static String serialize(Transaction transaction) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(transaction);
            return json;
        }catch(JsonProcessingException ex) {
            throw new TransactionSerializationException(String
                    .format("failed to sterilize transaction with message: %s.",ex.getMessage()));
        }
    }
}
