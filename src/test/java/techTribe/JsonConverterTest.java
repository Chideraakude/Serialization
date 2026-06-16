package techTribe;

import org.junit.jupiter.api.Test;
import techTribe.data.Transaction;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonConverterTest {

    @Test
    void testCanSerializeTransactionToJson(){
        String expected = "{\"sender\":\"Ene\",\"recipient\":\"Victor\",\"amount\":\"10\",\"timeCreated\":null,\"id\":\"1\"}";
        Transaction transaction = new Transaction();
        transaction.setId("1");
        transaction.setAmount(BigDecimal.TEN.toString());
        transaction.setSender("Ene");
        transaction.setRecipient("Victor");
        String json = JsonConverter.serialize(transaction);
        assertNotNull(json);
        assertEquals(expected, json);
        assertTrue(json.contains("{") &&json.contains("id") && json.contains("1") && json.contains("}"));

    }

    @Test
    void testCanDeserializeTransactionFromJson(){
        String json = "{\"sender\":\"Ene\",\"recipient\":\"Victor\",\"amount\":\"10\",\"id\":\"1\"}";
        Transaction transaction = JsonConverter.deserialize(json);
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
    void testCanSerializeTransactionWithLocalDateTimeToJson() {

        Transaction transaction = new Transaction();
        transaction.setId("1");
        transaction.setAmount(BigDecimal.TEN.toString());
        transaction.setSender("Ene");
        transaction.setRecipient("Victor");
        transaction.setTimeCreated(LocalDateTime.of(2026, 6, 15, 0, 0));

        String json = JsonConverter.serialize(transaction);

        assertNotNull(json);

        assertTrue(json.contains("\"sender\":\"Ene\""));
        assertTrue(json.contains("\"recipient\":\"Victor\""));
        assertTrue(json.contains("\"amount\":\"10\""));
        assertTrue(json.contains("\"id\":\"1\""));
        assertTrue(json.contains("\"timeCreated\""));

        assertTrue(json.contains("2026-06-15T00:00"));
    }

}