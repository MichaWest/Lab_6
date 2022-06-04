package ru.itmo.michawest.lab6;


import ru.itmo.michawest.lab6.collection.PersonCollection;
import ru.itmo.michawest.lab6.commands.CommandExecutorServer;
import ru.itmo.michawest.lab6.fileManager.FileWorker;
import ru.itmo.michawest.lab6.inputManager.ConsoleInput;
import ru.itmo.michawest.lab6.inputManager.InputAll;

import java.io.IOException;


public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            System.out.println("Сервер запущен.");
            PersonCollection personCollection = new PersonCollection();
            FileWorker fileWorker = new FileWorker();
            InputAll console = new ConsoleInput();
            int port = Integer.parseInt(args[0]);
            String path = "MICHELLE";
            try {
                fileWorker.setPath(path);
            } catch (NullPointerException e) {
                System.out.println("Невозможно загрузить коллекцию. Файл не найден.");
            }
            if (!personCollection.deserializeCollection(fileWorker.read())) {
                System.out.println("В файле ошибка");
                personCollection.clear();
            }
            CommandExecutorServer command = new CommandExecutorServer(fileWorker, personCollection, console, port);
            command.serverMode();
        }catch (NullPointerException e){
            System.out.println("Не задан порт. Завершение работы программы.");
        }catch (IllegalArgumentException e){
            System.out.println("Некорректное значение порта. Завершение работы приложения.");
        }
    }

}
