package ru.molokoin.jobs.client;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Стартовало клиетское приложение ...");
        new Client("localhost", 10001);
    }
    
}
