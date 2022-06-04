package ru.itmo.michawest.lab6.commands;

public class Exception extends Command {
    protected String nameOfCommand = "exception";
    private String message = "На сервере возникла ошибка";

    public Exception() {
        super("exception");
    }


    @Override
    public void getResult() {
        System.out.println(message);
    }

    public void setMessage(String mes) {
        message = mes;
    }
}
