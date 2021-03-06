package pl.sdacademy.chat.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatedChatMessageTest {

    @Test
    public void shouldCopyValueFromChatMessage() {
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