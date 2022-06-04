package ru.itmo.michawest.lab6.exceptions;

public class InvalidEnumException extends ParameterException {
    public InvalidEnumException() {
        super("неправильная константа");
    }
}
