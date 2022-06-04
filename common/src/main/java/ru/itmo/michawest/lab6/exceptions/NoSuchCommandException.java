package ru.itmo.michawest.lab6.exceptions;

public class NoSuchCommandException extends CommandException {
    public NoSuchCommandException() {
        super("неправильная команда");
    }
}
