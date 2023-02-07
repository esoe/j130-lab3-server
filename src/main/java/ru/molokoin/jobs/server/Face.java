package ru.molokoin.jobs.server;

import java.io.Console;

/**
 * Консольный интерфейс
 */
public class Face {
    private Console console;

    public Face(){
        System.out.println("Запуск консольного интерфейса ...");
        initConsole();//проверка наличия консоли
        hello();
    }
    /**
     * Проверка наличия консоли
     */
    public void initConsole(){
        System.out.println("Проверка наличия системной консоли ...");
        console = System.console();
        if (console == null) {
            System.err.println("Консоль не обнаружена ...");
            System.out.println("Инициировано завершение работы приложения ...");
            System.exit(1);
        }
        System.out.println("Системная консоль обнаружена ...");
    }

    public void hello(){
        System.out.println(">>> Описание доступного функционала, порядка взаимодействия с приложением <<<");
        help();
    }

    /**
     * Вывод в консоль сведений по доступным обращениям к серверу
     */
    public void help(){
        System.out.println("Перечень доступных пользователю команд:");
        System.out.println("\\help      - вызов в консоль перечня доступных команд по настройке сервера");
        System.out.println("\\port      - установить порт сервера (порт необходимо задать до запуска сервера)");
        System.out.println("\\start     - запуск сервера");
        System.out.println("\\quit      - выход из приложения");
        listen();
    }
    /**
     * Запуск сервера
     * TODO запуск сервера надо реализовать в отдельном потоке, чтобы предотвратить блокирование доступа пользователя к интерфейсу
     * @param port
     */
    public void start(int port){
        System.out.println("Попытка запуска сервера на порту " + port + " ...");
        new Server(port).start();//заблокирует работу интерфейса
        listen();
    }
    
    /**
     * Получение номера порта для запуска сервера
     * TODO - порт писать в properties (файл настроек сервера)  
     * @return
     */
    public int getPort(){
        System.out.println("Получение номера порта для запуска сервера ...");
        System.out.println("Укажите номер порта для запуска сервера");
        int port = Integer.valueOf(console.readLine());//TODO обработать случай не корректного ввода порта пользователем
        return port;
    }
    /**
     * Включение режима прослушивания команд пользователя,
     * Запуск команд пользователя на исполнение
     */
    public void listen(){
        System.out.println("Для получения помощи введите \\help");
        System.out.println("Введите команду серверу:");
        switch (console.readLine()) {
            case ("\\start"):
                start(getPort());
                break;
            case ("\\quit"):
                //getPort();
                break;
            default:
                System.out.println("Указана не верная команда, попробуйте еще раз ...");
                listen();
                break;
        }
    }
}