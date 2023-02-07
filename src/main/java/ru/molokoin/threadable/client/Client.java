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
    private BufferedReader console; // поток чтения с консоли
    // private String nickname; // имя клиента
    // private Date time;
    // private String dtime;
    // private SimpleDateFormat dt1;
    
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
            //pressNickname(); // перед началом необходимо спросит имя
            new MessageReader(this).start(); // нить читающая сообщения из сокета в бесконечном цикле
            new WriteMsg().start(); // нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле
        } catch (IOException e) {
            downService();
        }
    }
    
    /**
     * просьба ввести имя,
     * и отсылка эхо с приветсвием на сервер
     */
    
    // private void pressNickname() {
    //     System.out.print("Press your nick: ");
    //     try {
    //         nickname = console.readLine();
    //         out.write("Hello " + nickname + "\n");
    //         out.flush();
    //     } catch (IOException ignored) {
    //     }
    // }
    
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
    
    // // нить чтения сообщений с сервера
    // private class ReadMsg extends Thread {
    //     @Override
    //     public void run() {
    //         String str;
    //         try {
    //             while (true) {
    //                 str = in.readLine(); // ждем сообщения с сервера
    //                 if (str.equals("stop")) {
    //                     Client.this.downService(); // харакири
    //                     break; // выходим из цикла если пришло "stop"
    //                 }
    //                 System.out.println(str); // пишем сообщение с сервера на консоль
    //             }
    //         } catch (IOException e) {
    //             Client.this.downService();
    //         }
    //     }
    // }
    
    // нить отправляющая сообщения приходящие с консоли на сервер
    public class WriteMsg extends Thread {
        @Override
        public void run() {
            while (true) {
                String line;
                try {
                    // time = new Date(); // текущая дата
                    // dt1 = new SimpleDateFormat("HH:mm:ss"); // берем только время до секунд
                    // dtime = dt1.format(time); // время
                    line = console.readLine(); // сообщения с консоли
                    // if (userWord.equals("stop")) {
                    //     out.write("stop" + "\n");
                    //     Client.this.downService(); // харакири
                    //     break; // выходим из цикла если пришло "stop"
                    // } else {
                        //out.write("(" + dtime + ") " + nickname + ": " + userWord + "\n"); // отправляем на сервер
                        out.write(line + "\n");
                    //}
                    out.flush(); // чистим
                } catch (IOException e) {
                    downService(); // в случае исключения тоже харакири
                }
            }
        }
    }
}
