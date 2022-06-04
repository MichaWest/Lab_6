package ru.itmo.michawest.lab6.commands;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryCommand extends Command implements Serializable {
    protected String nameOfCommand = "history";

    public HistoryCommand() {
        super("history");
    }

    @Override
    public void getResult() {
        for(String comand: this.history){
            System.out.println(comand);
        }
    }


}
