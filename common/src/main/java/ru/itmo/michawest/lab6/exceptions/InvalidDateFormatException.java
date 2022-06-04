package ru.itmo.michawest.lab6.exceptions;

public class InvalidDateFormatException extends ParameterException {
    public InvalidDateFormatException() {
        super("формат даты должен быть HH:mm:ss");
    }
}
