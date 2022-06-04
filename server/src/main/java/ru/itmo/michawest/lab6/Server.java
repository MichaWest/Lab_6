package ru.itmo.michawest.lab6;


import ru.itmo.michawest.lab6.collection.PersonCollection;
import ru.itmo.michawest.lab6.commands.CommandExecutorServer;
import ru.itmo.michawest.lab6.fileManager.FileWorker;
import ru.itmo.michawest.lab6.inputManager.ConsoleInput;
import ru.itmo.michawest.lab6.inputManager.InputAll;

import java.io.IOException;


public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PersonCollection personCollection = new PersonCollection();
        FileWorker fileWorker = new FileWorker();
        InputAll console = new ConsoleInput();
        String path = "MICHELLE";
        try {
            fileWorker.setPath(path);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        if (!personCollection.deserializeCollection(fileWorker.read())) {
            System.out.println("В файле ошибка");
            personCollection.clear();
        }
        CommandExecutorServer command = new CommandExecutorServer(fileWorker, personCollection, console);
        command.serverMode();

    }

}
