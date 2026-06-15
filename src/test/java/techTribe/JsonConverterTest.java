package techTribe;

import org.junit.jupiter.api.Test;
import techTribe.data.Transaction;

import java.math.BigDecimal;

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

}