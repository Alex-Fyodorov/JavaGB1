package javathree.sem5;

import lombok.Data;

@Data
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
