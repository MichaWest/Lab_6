package ru.itmo.michawest.lab6.exceptions;

import ru.itmo.michawest.lab6.commands.Command;

public class EmptyCollectionException extends CommandException {
    public EmptyCollectionException() {
        super("коллекция пустая");
    }
}
