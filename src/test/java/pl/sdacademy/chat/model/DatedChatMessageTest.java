package pl.sdacademy.chat.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DatedChatMessageTest {

    @Test
    public void test() {
        // Given
        ChatMessage chatMessage = new ChatMessage("Grzesiek", "Ala ma kota");
        // When
        DatedChatMessage datedChatMessage = new DatedChatMessage(chatMessage);
        // Then
        assertEquals(datedChatMessage.getMessage(), chatMessage.getMessage());
        assertEquals(datedChatMessage.getAuthor(), chatMessage.getAuthor());
        assertNotNull(datedChatMessage.getReciveDate());
    }


}