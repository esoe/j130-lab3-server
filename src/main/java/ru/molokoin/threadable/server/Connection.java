package ru.molokoin.threadable.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Класс рализующий обработку подключения клиента к серверу
 * - Подключение вынесено в отдельный поток, чтобы обеспечить возможность подключения к серверу множества клиентов
 */
public class Connection extends Thread{
    //private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public Connection(Socket socket) throws IOException{
        System.out.println("Соединение с клиентом установлено ...");
        //this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    
    /**
     * Запуск потоков:
     * - получения сообщений,
     * - отправки сообщений,
     * - получения команд пользователя из консоли
     */
    @Override
    public void run(){
        String line;
        try{
            while (true){
                line = in.readLine();
                System.out.println("Получено сообщение клиента: " + line);
                try{
                    out.write(getTime() + "\n");
                    System.out.println(getTime());
                    out.flush();
                }catch(IOException e){
                    System.out.println("Ошибка отправки сообщения: " + e.getMessage());
                }
            }
        } catch(IOException e){
            System.out.println("Ошибка получения сообщения: " + e.getMessage());
        }
    }
    /**
     * Отправка сообщений клиенту:
     * - для отправки даты и времени получения сервером сообщения
     * @param message
     */
    public void send (String message){
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            System.out.println("Ошибка отправки сообщения: " + e.getMessage());
        }
    }
    /**
     * Получение текущего времени на сервере
     * @return
     */
    public String getTime(){
        Date time;
        SimpleDateFormat pattern;
        time = new Date();
        pattern = new SimpleDateFormat("HH:mm:ss");
        return pattern.format(time);
    }
}
