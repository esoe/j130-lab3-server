package ru.molokoin.jobs.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Запуск сервера
 */
public class Server extends Thread{
    private int port;
    private ServerSocket mainSocket;
    public Server (int port){
        setPort(port);
    }
    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }
    /**
     * @param mainSocket the mainSocket to set
     * @throws IOException
     */
    public ServerSocket getMainSocket() throws IOException {
        if (mainSocket == null){
            this.mainSocket = new ServerSocket(port);
        }
        return mainSocket;
    }
    /**
     * 
     */
    @Override
    public void run(){
        try{
            new Connection(getMainSocket().accept());
            System.out.println(mainSocket.getLocalPort());
            System.out.println(mainSocket.getInetAddress().getHostName());
            System.out.println(mainSocket.getLocalPort());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
}
