package ru.itmo.michawest.lab6.exceptions;

/**
 * thrown when file doesn't exist
 */
public class FileNotExistException extends FileException {
    public FileNotExistException() {
        super("не могу найти файл");
    }
}
