package ru.itmo.michawest.lab6.exceptions;

/**
 * class for exception caused by empty path input
 */
public class EmptyPathException extends FileException {
    public EmptyPathException() {
        super("путь пустой");
    }
}
