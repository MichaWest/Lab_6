package ru.itmo.michawest.lab6.commands;

public class ExitCommand extends Command {
    protected String nameOfCommand = "exit";

    public ExitCommand() {
        super("exit");
    }

    @Override
    public void getResult() {
        System.out.println("Good buy");
    }
}
