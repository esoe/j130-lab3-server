package ru.molokoin.threadable.client;

public class Runner {
    public static String host = "localhost";
    public static int port = 10001;
    
    public static void main(String[] args) {
        new Client(host, port);
    }
    
}
