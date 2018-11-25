package pl.sdacademy.chat.model;

import java.time.LocalDateTime;

public class DatedChatMessage extends ChatMessage {


    private final LocalDateTime reciveDate;


    public DatedChatMessage(ChatMessage chatMessage) {
        super(chatMessage.getAuthor(), chatMessage.getMessage());

        reciveDate = LocalDateTime.now();
    }



    public LocalDateTime getReciveDate() {
        return reciveDate;
    }
}
