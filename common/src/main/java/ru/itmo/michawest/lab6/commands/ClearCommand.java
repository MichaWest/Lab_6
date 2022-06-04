package ru.itmo.michawest.lab6.commands;

import ru.itmo.michawest.lab6.collection.PersonCollection;

public class ClearCommand extends Command {
    protected String nameOfCommand = "clear";

    public ClearCommand() {
        super("clear");
    }

    @Override
    public void getResult() {
        System.out.println("Коллекция очищена");
    }

    public void setCollection(PersonCollection col){
        this.collection = col;
    }
}
