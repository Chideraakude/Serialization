package techTribe.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Transaction {
    @Id
    private String Id;
    private String sender;
    private String recipient;
    private String amount;
    LocalDateTime timeCreated;
}
