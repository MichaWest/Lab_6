package ru.itmo.michawest.lab6.commands;

import ru.itmo.michawest.lab6.collection.PersonCollection;
import ru.itmo.michawest.lab6.data.Color;
import ru.itmo.michawest.lab6.data.Person;

import java.util.Vector;

public class CountByHairColorCommand extends Command {
    protected String nameOfCommand = "count_by_hair_color";
    private int count;
    private Color color;

    public CountByHairColorCommand() {
        super("count_by_hair_color");
    }

    @Override
    public void getResult() {
        System.out.println("Count person with color: "+color+" -"+count);
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setCount(int i){
        count = i;
    }

    public Color getColor(){
        return color;
    }

}
