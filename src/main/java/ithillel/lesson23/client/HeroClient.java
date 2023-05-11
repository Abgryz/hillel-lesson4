package ithillel.lesson23.client;

import lombok.SneakyThrows;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HeroClient {
    Socket socket;

    @SneakyThrows
    public HeroClient(String host, int port) {
        socket = new Socket(host, port);
    }

    public void connect() {
        System.out.println("Connect to server...");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)){
            Scanner scan = new Scanner(System.in);
            String userInput;

            while ((userInput = scan.nextLine()) != null) {
                out.println(userInput);
                System.out.println(in.readLine());
                if (userInput.equals("-exit")){
                    break;
                }
            }
            socket.close();
        } catch (IOException e){
            System.out.println("Connection with server failed");
        }
    }
}
