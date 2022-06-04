package ru.itmo.michawest.lab6.commands;

import ru.itmo.michawest.lab6.collection.PersonCollection;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Command implements Serializable {
    protected String nameOfCommand;
    protected ArrayList<String> history;
    protected PersonCollection collection;

    public Command(String name) {
        nameOfCommand = name;
        history = new ArrayList<>();
    }

    public String getNameOfCommand() {
        return nameOfCommand;
    }

    public abstract void getResult();

    public PersonCollection getCollection() {
        return collection;
    }

    public void addCommand(String command){
        if(history == null){
            history.add(command);
        }
        else {
            if (history.size() < 7) {
                history.add(command);
            } else {
                history.remove(0);
                history.add(command);
            }
        }
    }

    public void setCollection(PersonCollection collection) {
        this.collection = collection;
    }
}
