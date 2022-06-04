package ru.itmo.michawest.lab6.exceptions;

public class InvalidNumberException extends ParameterException {
    public InvalidNumberException(String message) {
        super(message);
    }

    public InvalidNumberException() {
        super("неправильно введено значение");
    }
}
