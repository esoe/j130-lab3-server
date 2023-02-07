package ru.molokoin.threadable.server;
/**
 * Консольный интерфейс сервера обмена сообщениями,
 * - работает в основном потоке, прерывания которого исключены запуском осталных емких процессов в отдельных потоках
 */
public class ServerFace {
    public static final int PORT = 10001;//передает пользователь
    public static void main(String[] args) {
        new Server(PORT).start();
    }
}
