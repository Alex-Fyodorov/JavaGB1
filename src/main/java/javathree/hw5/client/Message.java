package javathree.hw5.client;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String from;
    private String to;
    private String message;
    private Boolean all;

    public Message(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.all = false;
    }

    public Message(String from, String message) {
        this.from = from;
        this.message = message;
        this.all = true;
    }
}
