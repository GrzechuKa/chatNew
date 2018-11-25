package pl.sdacademy.chat.server;

import pl.sdacademy.chat.model.ChatMessage;
import pl.sdacademy.chat.model.DatedChatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatLog {

    private Map<Socket, ObjectOutputStream> registerClients;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ChatLog() {
        registerClients = new ConcurrentHashMap<>();
    }

    public boolean register(Socket client) {
        try {
            ObjectOutputStream streamToClinet = new ObjectOutputStream(client.getOutputStream());
            registerClients.put(client, streamToClinet);
            return true;
        } catch (IOException e) {
            System.out.println("### Someone tried to connect but was rejecteted ###");
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregister(Socket client) {
        try {
            ObjectOutputStream unregister = registerClients.get(client);
            unregister.close();
            registerClients.remove(client);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public void acceptMessege(ChatMessage message) {
        DatedChatMessage datedChatMessage = new DatedChatMessage(message);
        printMessage(datedChatMessage);
        updatedClients(datedChatMessage);
    }

    private void printMessage(DatedChatMessage datedChatMessage){
        System.out.print("<" + dateTimeFormatter.format(datedChatMessage.getReciveDate()) + ">");
        System.out.print("<" + datedChatMessage.getAuthor() + "> : ");
        System.out.println("<" + datedChatMessage.getMessage() + ">");
    }

    private void updatedClients (DatedChatMessage datedChatMessage){
        Set<Map.Entry<Socket, ObjectOutputStream>> allEntries = registerClients.entrySet();
        for (Map.Entry<Socket, ObjectOutputStream> entry: allEntries){
            ObjectOutputStream connectionToClient = entry.getValue();
            try {
                connectionToClient.writeObject(datedChatMessage);
                connectionToClient.flush();
            } catch (IOException e) {
                unregister(entry.getKey());
            }
        }
    }
}
