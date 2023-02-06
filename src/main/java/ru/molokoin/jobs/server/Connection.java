package ru.molokoin.jobs.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
/**
 * Класс обработки данных из потоков соединения сервера с клиентом
 * Использование:
 * new Connection()
 */
public class Connection implements Runnable{
    private BufferedReader in; //поток чтения из сокета
    private BufferedWriter out; //поток завписи в сокет
    private Socket socket;
    /**
     * Главный конструктор класса, получает на вход сокет для соединения с клиентом
     * serverSocket.accept(), инициирует поле socket
     * запускает обработку потоков ввода-вывода
     * @param socket
     */
    public Connection(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }
    
}
