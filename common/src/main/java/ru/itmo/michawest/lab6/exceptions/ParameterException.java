package ru.itmo.michawest.lab6.exceptions;

/**
 * base class for all exceptions caused by invalid input
 */
public class ParameterException extends Exception {
    public ParameterException(String msg) {
        super(msg);
    }
}
