package ru.molokoin.jobs.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
/**
 * Класс обработки данных из потоков соединения сервера с клиентом
 * Использование:
 * new Connection()
 */
public class Connection {
    private BufferedReader in; //поток чтения из сокета
    private BufferedWriter out; //поток завписи в сокет
    private Socket socket;
    /**
     * Главный конструктор класса, получает на вход сокет для соединения с клиентом
     * serverSocket.accept(), инициирует поле socket
     * запускает обработку потоков ввода-вывода
     * @param socket
     * @throws IOException
     */
    public Connection(Socket socket) throws IOException{
        System.out.println("Соединение с клиентом установлено ...");
        this.socket = socket;
        read(socket.getInputStream());
        write(socket.getOutputStream());
    }
    public void printStats(){
        System.out.println("Сведения о соединении:");
        System.out.println("HOST          : " + socket.getInetAddress().getHostAddress());
        System.out.println("PORT          : " + socket.getPort());
        System.out.println("TIME (millis) : " + System.currentTimeMillis());
    }
    /**
     * Читаем сообщение из входящего потока
     * @param is
     * @return
     * @throws IOException
     */
    public String read (InputStream is){
        System.out.println("Получение сообщения клиента ...");
        in = new BufferedReader(new InputStreamReader(is));
        System.out.println("Чтение сообщения клиента ...");
        String message = "";
        try {
            while ((message = in.readLine()) != null){
                System.out.println(message );
            }
            //message = in.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("Получено: " + message);
        return message;
    }
    /**
     * Пишем сообщение в исходящий поток
     * @param os
     * @throws IOException
     */
    public void write(OutputStream os) throws IOException{
        out = new BufferedWriter(new OutputStreamWriter(os));
        out.write("" + System.currentTimeMillis());
    }
}
