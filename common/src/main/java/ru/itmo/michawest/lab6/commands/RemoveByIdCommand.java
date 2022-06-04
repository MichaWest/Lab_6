package ru.itmo.michawest.lab6.commands;

public class RemoveByIdCommand extends Command {
    protected String nameOfCommand = "remove_by_id";
    private int removeID;

    public RemoveByIdCommand() {
        super("remove_by_id");
    }

    @Override
    public void getResult() {
        System.out.println("Элемент с id " + removeID + " успешно удален");
    }

    public void setRemoveID(int id) {
        removeID = id;
    }

    public int getRemoveID() {
        return removeID;
    }
}
