package ru.itmo.michawest.lab6.inputManager;

import ru.itmo.michawest.lab6.fileManager.FileWorker;

import java.util.Scanner;

public class FileInput extends InputAll {
    public FileInput(String path){
        super(new Scanner(new FileWorker(path).read()));
        getScanner().useDelimiter("\n");
    }
}