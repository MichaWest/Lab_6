package ru.itmo.michawest.lab6.commands;


import ru.itmo.michawest.lab6.collection.PersonCollection;

public class ShowCommand extends Command {
    protected String nameOfCommand = "show";
    PersonCollection collection;

    public ShowCommand() {
        super("show");
    }

    @Override
    public void getResult() {
        System.out.println(collection.serializeCollection());
    }
}
