package ru.molokoin.threadable.client;

import java.io.IOException;

/**
 * Класс, представляющий отдельный поток,
 * читающий в бесконечном цикле сообщения от сервера
 * - сервер пересылает дату и время получения клиентских сообщений
 */
public class MessageReader extends Thread {
    Client client;

    public MessageReader(Client client){
        this.client = client;
    }
    @Override
    public void run() {
        String line;
        try {
            while (true) {
                line = client.in.readLine(); // ждем сообщения с сервера
                // if (line.equals("stop")) {
                //     client.downService(); // харакири
                //     break; // выходим из цикла если пришло "stop"
                // }
                System.out.println(line); // пишем сообщение с сервера на консоль
            }
        } catch (IOException e) {
            client.downService();
        }
    }
}
