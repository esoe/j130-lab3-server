package ru.molokoin.threadable.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
/**
 * Класс рализующий обработку подключения клиента к серверу
 * - Подключение вынесено в отдельный поток, чтобы обеспечить возможность подключения к серверу множества клиентов
 */
public class Connection extends Thread{
    private Socket socket;
    private BufferedReader in;//только на случай обмена текстовыми сообщениями
    private BufferedWriter out;//лучше настроить прием сообщения с указанием типа получаемых данных или самостоятельную валидацию типов сервером

    public Connection(Socket socket) throws IOException{
        System.out.println("Соединение с клиентом установлено ...");
        this.socket = socket;
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
        //StringBuilder message = new StringBuilder();
        String line;
        try{
            while (true){
                line = in.readLine();
                System.out.println("Получено сообщение клиента: " + line);
                try{
                    out.write("OK" + "\n");//поправить, для отправки текущего времени сервера
                    //out.flush();
                }catch(IOException e){
                    System.out.println("Ошибка отправки сообщения: " + e.getMessage());
                }
            }
            //line = in.readLine();
            //System.out.println("Получено сообщение клиента: " + line);
            
        } catch(IOException e){
            System.out.println("Ошибка получения сообщения: " + e.getMessage());
        }
        // try{
        //     //бесконечный цикл приема строк сообщения от клиента
        //     while (true){
        //         line = in.readLine();
        //         if (line.equals("\\stop")){
        //             //TODO можно прописать прерывание работы сервера клиентом
        //             //TODO можно предусмотреть закрытие подключения клиента и удаление его сокета из перечня активных подключений
        //             System.out.println("Клиент просит завершить сессию ...");
        //         }
        //         message.append(line + "\n");

        //     }
        // }catch(IOException e){
        //     System.out.println("Ошибка получения сообщения от клиента: " + e.getMessage());
        // }
        // System.out.println("Сообщение клиента получено:");
        // System.out.println(message);
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
}
