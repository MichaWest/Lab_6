package ru.itmo.michawest.lab6;

import ru.itmo.michawest.lab6.commands.CommandExecutorClient;
import ru.itmo.michawest.lab6.inputManager.ConsoleInput;
import ru.itmo.michawest.lab6.inputManager.InputAll;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Client {
    protected ObjectInputStream readOb;

    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Клиент запущен.");
            int port = Integer.parseInt(args[0]);
            InputAll console = new ConsoleInput();
            CommandExecutorClient command = new CommandExecutorClient(console,port);
            command.consoleMode();
        }catch (NullPointerException e){
            System.out.println("Вы не ввели порт. Завершение работы...");
        }catch(IllegalArgumentException e){
            System.out.println("Некорректный порт. Завершение работы.");
        }
    }
}
