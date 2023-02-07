package ru.molokoin.threadable.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * реализация основного потока сервера
 * - сервер вынесен в самостоятельный поток, чтобы не прерывать работу интерфейса
 */
public class Server extends Thread{
    private int port;
    private LinkedList<Connection> connections = new LinkedList<Connection>();

    public Server(int port){
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Сервер запущен ...");
            while (true){
                System.out.println("Сервер ожидает подключение клиента ...");
                Socket socket = ss.accept();
                connections.add(new Connection(socket));
            }
        } catch (IOException e) {
            System.out.println("Запуск сервера не удался ...");
            System.out.println("Сообщение об ошибке: " + e.getMessage());
        }
    }
}
