package techTribe.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Transaction {
    @Id
    private String Id;
    private String sender;
    private String recipient;
    private String amount;
}
