package ru.itmo.michawest.lab6.commands;

import ru.itmo.michawest.lab6.data.Person;

public class AddCommand extends Command {
    protected String nameOfCommand = "add";
    private Person person;

    public AddCommand() {
        super("add");
    }

    @Override
    public void getResult() {
        System.out.println("Person " + person.getName() + " добавлен в коллекцию");
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person p) {
        this.person = p;
    }

}
