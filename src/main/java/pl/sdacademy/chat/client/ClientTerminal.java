package pl.sdacademy.chat.client;

import pl.sdacademy.chat.model.ChatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTerminal implements Runnable {

    private final Socket connectionToServer;

    public ClientTerminal() throws IOException {
        connectionToServer = new Socket("IP", 5567);
    }

    @Override
    public void run() {

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectionToServer.getOutputStream())) {
            System.out.print("Give nick");
            String nickUser = new Scanner(System.in).nextLine();
            String message;
            while (true) {
                System.out.print("Give you message");
                message = new Scanner(System.in).next();
                ChatMessage chatMessage = new ChatMessage(nickUser, message);
                objectOutputStream.writeObject(chatMessage);
                objectOutputStream.flush();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Can not connected to server");
            e.printStackTrace();
        }


        //wytworzenie strumieni musi byc w try with resoursesm ->try
        //pobierz nick
        //petla az user wpisze exit
        //   pobierac test od uzytkownik
        //   tworzymy nowy obiekt chatmessage
        // wysylamie go do serwera
//             komunikat exit tez wsylamy na serwer
    }
}
