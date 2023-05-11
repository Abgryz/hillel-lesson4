package ithillel.lesson23.client;

public class Client2 {
    public static void main(String[] args) {
        new HeroClient("localhost", 8080).connect();
    }
}
