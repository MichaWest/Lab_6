package ru.itmo.michawest.lab6.commands;


import ru.itmo.michawest.lab6.exceptions.EmptyCollectionException;

public class InfoCommand extends Command {
    protected String nameOfCommand = "info";

    public InfoCommand() {
        super("info");
    }

    @Override
    public void getResult() {
        if (collection.getCollection() == null) throw new EmptyCollectionException();
        if (collection.getCollection().isEmpty()) throw new EmptyCollectionException();
        System.out.println(collection.getInfo());
    }

}
