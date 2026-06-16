package techTribe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import techTribe.data.Transaction;
import techTribe.exception.JsonToTransactionDeserializationFailedException;
import techTribe.exception.TransactionSerializationException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class JsonConverter {
    public static String serialize(Transaction transaction) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            String json = objectMapper.writeValueAsString(transaction);
            return json;
        }catch(JsonProcessingException ex) {
            throw new TransactionSerializationException(String
                    .format("failed to sterilize transaction with message: %s.",ex.getMessage()));
        }
    }

    public static Transaction deserialize(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            Transaction transaction = objectMapper.readValue(json, Transaction.class);
            return transaction;
        }catch(IOException ex){
            throw new JsonToTransactionDeserializationFailedException(ex.getMessage());
        }
        }


    public static List<Transaction> deserialize(Path path) {
        try(InputStream inputStream = Files.newInputStream(path);){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            List<Transaction> transactions = objectMapper.readValue(inputStream, new TypeReference<>() {});
            System.out.println(transactions);
            return transactions;
        }catch (IOException ex){
            throw new JsonToTransactionDeserializationFailedException(ex.getMessage());
        }
    }




}
