package ru.itmo.michawest.lab6.commands;

public class HelpCommand extends Command {
    protected String nameOfCommand = "help";

    public HelpCommand() {
        super("help");
    }

    public String getNameOfCommand() {
        return nameOfCommand;
    }

    @Override
    public void getResult() {
        System.out.println(
                "help : вывести справку по доступным командам\n" +
                        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                        "add {element} : добавить новый элемент в коллекцию\n" +
                        "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                        "remove_by_id id : удалить элемент из коллекции по его id\n" +
                        "clear : очистить коллекцию\n" +
                        "save : сохранить коллекцию в файл\n" +
                        "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                        "exit : завершить программу (без сохранения в файл)\n" +
                        "remove_first : удалить первый элемент из коллекции\n" +
                        "reorder : отсортировать коллекцию в порядке, обратном нынешнему\n" +
                        "history : вывести последние 7 команд (без их аргументов)\n" +
                        "min_by_weight : вывести любой объект из коллекции, значение поля weight которого является минимальным\n" +
                        "group_counting_by_nationality : сгруппировать элементы коллекции по значению поля nationality, вывести количество элементов в каждой группе\n" +
                        "count_by_hair_color hairColor : вывести количество элементов, значение поля hairColor которых равно заданному");
    }
}
