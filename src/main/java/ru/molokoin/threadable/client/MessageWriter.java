package ru.molokoin.threadable.client;

import java.io.IOException;

public class MessageWriter extends Thread{
    Client client;
    public MessageWriter(Client client){
        this.client = client;

    }

    @Override
    public void run() {
        while (true) {
            String line;
            try {
                // time = new Date(); // текущая дата
                // dt1 = new SimpleDateFormat("HH:mm:ss"); // берем только время до секунд
                // dtime = dt1.format(time); // время
                line = client.console.readLine(); // сообщения с консоли
                // if (userWord.equals("stop")) {
                //     out.write("stop" + "\n");
                //     Client.this.downService(); // харакири
                //     break; // выходим из цикла если пришло "stop"
                // } else {
                    //out.write("(" + dtime + ") " + nickname + ": " + userWord + "\n"); // отправляем на сервер
                    client.out.write(line + "\n");
                //}
                client.out.flush(); // чистим
            } catch (IOException e) {
                client.downService(); // в случае исключения тоже харакири
            }
        }
    }
}
