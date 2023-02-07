package ru.molokoin.threadable.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
//import java.text.SimpleDateFormat;
//import java.util.Date;

class Client {
    private Socket socket;
    public BufferedReader in; // поток чтения из сокета
    public BufferedWriter out; // поток чтения в сокет
    public BufferedReader console; // поток чтения с консоли
    
    /**
     * Подключение к серверу, получение потоков:
     * - ввода данных с консоли
     * - входящего потока от сервера (in)
     * - исходящего потока от клиента (out)
     * 
     * Запуск нитей обработки потоков
     *
     * @param host
     * @param port
     */
    
    public Client(String host, int port) {
        System.out.println("Попытка подключения к серверу ...");
        try {
            socket = new Socket(host, port);
            System.out.println("Соединение с сервером установлено ...");
        } catch (IOException e) {
            System.out.println("Поключение к серверу не удалось: " + e.getMessage() + " ...");
        }
        try {
            System.out.println("Инициация потока чтения с консоли ...");
            console = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Инициация потока чтения данных с сервера ...");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Инициация потока передачи данных на сервер ...");
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new MessageReader(this).start();// нить читающая сообщения из сокета в бесконечном цикле
            new MessageWriter(this).start();// нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле
        } catch (IOException e) {
            downService();
        }
    }
    
    /**
     * закрытие сокета
     */
    public void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }
    
}