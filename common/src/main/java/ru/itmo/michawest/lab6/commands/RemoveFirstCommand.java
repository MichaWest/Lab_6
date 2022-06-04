package ru.itmo.michawest.lab6.commands;

public class RemoveFirstCommand extends Command {
    protected String nameOfCommand = "remove_first";

    public RemoveFirstCommand() {
        super("remove_first");
    }

    @Override
    public void getResult() {
        System.out.println("Первый элемент успешно удален");
    }

}
