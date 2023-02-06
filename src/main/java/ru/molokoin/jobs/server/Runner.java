package ru.molokoin.jobs.server;
/**
 * Класс запускает серверное приложение
 * - консольный интерфейс (в основном потоке)
 * - сервер для обмена сообщниями с клиентом (в самостоятельном потоке)
 */
public class Runner {
    public static void main(String[] args) {
        System.out.println("Стартовало серверное приложение ...");
        Face face = new Face();
        
    }
}
