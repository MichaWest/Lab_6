package ru.itmo.michawest.lab6.commands;

import ru.itmo.michawest.lab6.data.Person;

public class UpdateByIdCommand extends Command {
    protected String nameOfCommand = "update_by_id";
    private int newId;
    private Person per;

    public UpdateByIdCommand() {
        super("update_by_id");
    }

    @Override
    public void getResult() {
        System.out.println("Эдемент #"+ newId +" обновлен");
    }

    public void setNewId(int id){
        newId = id;
    }

    public int getNewId(){
        return newId;
    }

    public void setUpdatePerson(Person p){
        per = p;
    }

    public Person getUpdatePerson(){
        return per;
    }
}
