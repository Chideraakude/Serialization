package techTribe;

import org.junit.jupiter.api.Test;
import techTribe.data.Transaction;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonConverterTest {

    @Test
    void testCanSerializeTransactionToJson(){
        String expected = "{\"sender\":\"Ene\",\"recipient\":\"Victor\",\"amount\":\"10\",\"id\":\"1\"}";
        Transaction transaction = new Transaction();
        transaction.setId("1");
        transaction.setAmount(BigDecimal.TEN.toString());
        transaction.setSender("Ene");
        transaction.setRecipient("Victor");
        String json = JsonConverter.serialize(transaction);
        assertNotNull(json);
        assertEquals(expected, json);
    }

    @Test
    void testCanDeserializeTransactionFromJson(){
        String json = "{\"sender\":\"Ene\",\"recipient\":\"Victor\",\"amount\":\"10\",\"id\":\"1\"}";
        Transaction transaction =JsonConverter.deserialize(json);
        assertNotNull(transaction);
        assertEquals("10", transaction.getAmount());
        assertEquals("Ene", transaction.getSender());
    }
    @Test
    void testCanDeserializeJsonFile(){
        String jsonFilePath = "/home/semi/Desktop/Transaction";
        Path path = Paths.get(jsonFilePath, "test.json");
        List<Transaction> transactions = JsonConverter.deserialize(path);
        assertNotNull(transactions);
        assertEquals(3, transactions.size());
    }

    @Test
    void testCanSerializeTransactionWithLocalDateTimeToJson(){
        String expected  = "{\"sender\":\"Ene\",\"recipient\":\"Victor\",\"amount\":\"10\",\"timeCreated\":\"15/06/2026\"}";
        Transaction transaction = new Transaction();
        transaction.setId("1");
        transaction.setAmount(BigDecimal.TEN.toString());
        transaction.setSender("Ene");
        transaction.setRecipient("Victor");
        transaction.setTimeCreated(LocalDateTime.now());
        String json = JsonConverter.serialize(transaction);
        assertNotNull(json);
        assertEquals(expected, json);
    }

}