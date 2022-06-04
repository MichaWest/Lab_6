package ru.itmo.michawest.lab6.commands;

public class ExecuteScriptCommand extends Command {
    protected String nameOfCommand = "execute_script";

    public ExecuteScriptCommand() {
        super("execute_script");
    }

    @Override
    public void getResult() {
        System.out.println("Файл выполнился");
    }
}
