package javathree.hw5.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Message buildMessage(String loginFrom, String messageFromClient) {
        if (messageFromClient.startsWith("@")) {
            String[] split = messageFromClient.strip().split("\\s+");
            String loginTo = split[0].substring(1);
            String message = messageFromClient.replace("@" + loginTo + " ", "");
            return new Message(loginFrom, loginTo, message);
        } else {
            return new Message(loginFrom, messageFromClient);
        }
    }

    public String stringToJson(String from, String messageIn) {
        Message messageObj = buildMessage(from, messageIn);
        String jsonMessage = "";
        try {
            jsonMessage = objectMapper.writeValueAsString(messageObj);

        } catch (JsonProcessingException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return jsonMessage;
    }

    public Message jsonToMessage(String json) {
        Message message = null;
        try {
            message = objectMapper.readValue(json, Message.class);
        } catch (JsonProcessingException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return message;
    }
}
