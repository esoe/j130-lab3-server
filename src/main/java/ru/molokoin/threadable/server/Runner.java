package ru.molokoin.threadable.server;
/**
 * Консольный интерфейс сервера обмена сообщениями,
 * - работает в основном потоке, прерывания которого исключены запуском осталных емких процессов в отдельных потоках
 */
public class Runner {
    public static final int PORT = 10001;
    public static void main(String[] args) {
        new Server(PORT).start();
    }
}
