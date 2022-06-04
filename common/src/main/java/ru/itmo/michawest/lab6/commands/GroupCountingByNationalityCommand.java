package ru.itmo.michawest.lab6.commands;

import ru.itmo.michawest.lab6.collection.PersonCollection;
import ru.itmo.michawest.lab6.data.Country;
import ru.itmo.michawest.lab6.data.Person;

public class GroupCountingByNationalityCommand extends Command {
    protected String nameOfCommand = "group_counting_by_nationality";
    private PersonCollection group;
    private Country nationality;

    public GroupCountingByNationalityCommand() {
        super("group_counting_by_nationality");
    }

    @Override
    public void getResult() {

    }

    public void setGroup(PersonCollection group){
        this.group = group;
    }

    public void setNationality(Country nation){
        this.nationality = nation;
    }

    public Country getNationality(){
        return nationality;
    }
}
