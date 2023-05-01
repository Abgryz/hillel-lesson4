package ithillel.lesson23;

import ithillel.lesson22.HeroDto;
import ithillel.lesson22.HeroFabric;
import ithillel.lesson22.HeroService;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HeroServer {
    private final ServerSocket server;
    private final ExecutorService executor = Executors.newFixedThreadPool(100);
    private final List<Socket> clients;

    @SneakyThrows
    public HeroServer(int port){
        server = new ServerSocket(port);
        clients = new ArrayList<>();
    }

    public void start(){
        System.out.println("Server opened in port " + server.getLocalPort());
        new Thread(this::serverConsole).start();
        while (true){
            try {
                Socket client = server.accept();
                clients.add(client);
                executor.execute(() -> clientIO(client));
            } catch (IOException e){
                System.out.println("Cannot accept client: server is closed");
                return;
            }
        }
    }

    private void clientIO(Socket client){
        System.out.println("Client connected: " + client);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true))
        {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.printf("%s Received message: %s\n", client, inputLine);
                String[] inputArr = inputLine.split(" ", 2);
                
                switch (inputArr[0]) {
                    case "-exit" -> {
                        System.out.println("Closed connection with " + client);
                        client.close();
                        return;
                    }
                    case "-name" -> {
                        HeroService heroService = HeroFabric.createService(DBConnection.connectTo("test", "postgres", "12345678", new String[]{"localhost"}, new int[]{5432}));
                        List<HeroDto> heroes = heroService.getHeroesByName(inputArr[1]);
                        if(heroes.size() != 0){
                            out.println(heroes);
                        } else {
                            out.println(String.format("Hero '%s' not found", inputArr[1]));
                        }
                    }
                    default -> out.println(String.format("Command '%s' not exists", inputArr[0]));
                }
            }
        } catch (IOException e) {
            System.out.println("Connection failed: " + client);
        }
    }

    @SneakyThrows
    private void serverConsole(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            if(scanner.nextLine().equals("-close")){
                for(Socket client : clients){
                    client.close();
                }
                executor.shutdown();
                server.close();
                return;
            }
        }
    }

    public static void main(String[] args) {
        new HeroServer(8080).start();
    }
}
