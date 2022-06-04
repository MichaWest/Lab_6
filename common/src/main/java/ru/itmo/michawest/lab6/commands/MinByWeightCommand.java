package ru.itmo.michawest.lab6.commands;

import ru.itmo.michawest.lab6.data.Person;

public class MinByWeightCommand extends Command {
    protected String nameOfCommand = "min_by_weight";
    private Person minPerson;

    public MinByWeightCommand() {
        super("min_by_weight");
    }

    @Override
    public void getResult() {
        System.out.println("The person with min weight: " + minPerson);
    }

    public void setMinPerson(Person p) {
        minPerson = p;
    }

}
