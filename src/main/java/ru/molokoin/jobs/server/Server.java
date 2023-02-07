package ru.molokoin.jobs.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Запуск сервера
 */
public class Server{
    private int port;
    private ServerSocket mainSocket;
    public Server (int port){
        System.out.println("Инициация компонентов сервера ...");
        setPort(port);
    }
    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        System.out.println("Настройка порта для работы сервера ...");
        this.port = port;
    }
    /**
     * @param mainSocket the mainSocket to set
     * @throws IOException
     */
    public ServerSocket getMainSocket() throws IOException {
        System.out.println("Запуск сервера ...");
        mainSocket = new ServerSocket(port);
        System.out.println("Сервер запущен ...");
        return mainSocket;
    }
    public void start(){
        try{
            ServerSocket ss = getMainSocket();
            printStats();
            System.out.println("Сервер ждет подключения клиента ...");
            new Connection(ss.accept());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Вывод сведений о подключении в консоль
     */
    public void printStats(){
        System.out.println("Параметры работы сервера:");
        System.out.println("PORT : " + mainSocket.getLocalPort());
        System.out.println("HOST : " + mainSocket.getInetAddress().getHostName());
    }
}
