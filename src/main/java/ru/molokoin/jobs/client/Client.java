package ru.molokoin.jobs.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public String host = "localhost";
    public int port = 10001;
    private Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    private BufferedReader console; // поток чтения с консоли

    public Client(String host, int port){
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        try {
            // потоки чтения из сокета / записи в сокет, и чтения с консоли
            console = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //читаем сообщение пользователя
            String message = "are you alive?";
            //console.readLine();
            //передаем сообщение на сервер
            out.write(message);
            out.flush();

            //new ReadMsg().start(); // нить читающая сообщения из сокета в бесконечном цикле
            //new WriteMsg().start(); // нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле
        } catch (IOException e) {
            this.down();
        }
    }
    public void down(){
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.out.println("Работа приложени завершена с ошибками:");
            System.out.println(e.getMessage());
        }
    }
}
