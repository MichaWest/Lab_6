package ru.itmo.michawest.lab6.exceptions;

public class EmptyCollectionException extends CommandException {
    public EmptyCollectionException() {
        super("коллекция пустая");
    }
}
