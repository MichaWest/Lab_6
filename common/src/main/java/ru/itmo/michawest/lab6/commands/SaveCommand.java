package ru.itmo.michawest.lab6.commands;

public class SaveCommand extends Command {
    protected String nameOfCommand = "save";

    public SaveCommand() {
        super("save");
    }

    @Override
    public void getResult() {
        System.out.println("Коллекция успешно сохранена");
    }
    @Override
    public void setNameOfCommand(String str){super.nameOfCommand = str;
    }
}
