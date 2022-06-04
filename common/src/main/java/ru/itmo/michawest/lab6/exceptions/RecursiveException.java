package ru.itmo.michawest.lab6.exceptions;

public class RecursiveException extends FileException {
    public RecursiveException() {
        super("файл уже запущен");
    }
}
