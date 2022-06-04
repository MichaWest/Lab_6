package ru.itmo.michawest.lab6.commands;

public class ReorderCommand extends Command {
    protected String nameOfCommand = "reorder";

    public ReorderCommand() {
        super("reorder");
    }

    @Override
    public void getResult() {
        System.out.println("Успешно сменнен порядок сортировки");
    }
}
