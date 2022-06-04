package ru.itmo.michawest.lab6;

import ru.itmo.michawest.lab6.commands.CommandExecutorClient;
import ru.itmo.michawest.lab6.inputManager.ConsoleInput;
import ru.itmo.michawest.lab6.inputManager.InputAll;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Client {
    protected ObjectInputStream readOb;

    public static void main(String[] args) throws IOException {
        InputAll console = new ConsoleInput();
        CommandExecutorClient command = new CommandExecutorClient(console);
        command.consoleMode();
    }

}
