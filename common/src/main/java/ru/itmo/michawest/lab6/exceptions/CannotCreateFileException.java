package ru.itmo.michawest.lab6.exceptions;

public class CannotCreateFileException extends FileException {
    public CannotCreateFileException() {
        super("немогу создать файл");
    }
}
