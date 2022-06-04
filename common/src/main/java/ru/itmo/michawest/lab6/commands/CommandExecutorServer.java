package ru.itmo.michawest.lab6.commands;


import ru.itmo.michawest.lab6.collection.PersonCollection;
import ru.itmo.michawest.lab6.exceptions.CommandException;
import ru.itmo.michawest.lab6.exceptions.EmptyCollectionException;
import ru.itmo.michawest.lab6.exceptions.InvalidCommandArgumentException;
import ru.itmo.michawest.lab6.fileManager.FileWorker;
import ru.itmo.michawest.lab6.inputManager.InputAll;

import java.io.*;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandExecutorServer {

    static protected InetSocketAddress address = new InetSocketAddress("localhost", 6027);
    private InetSocketAddress addressOfSender;
    private PersonCollection collection;
    private DatagramChannel channel;
    private final FileWorker fileWorker;
    private InputAll input;
    private boolean run;
    private final List<String> history;

    public CommandExecutorServer(FileWorker fileWorker, PersonCollection collection, InputAll inputAll) {
        this.input = inputAll;
        this.collection = collection;
        this.fileWorker = fileWorker;
        history = new ArrayList<>();
    }

    public void serverMode() {
        try {
            try {
                channel = DatagramChannel.open();
                channel.bind(address);
                run = true;
                InputThread in = new InputThread();
                new Thread(in,"MyThread").start();
                while (run) {
                    Command command = getCommand();
                    System.out.println(command.getNameOfCommand());
                    runCommand(command);
                }
                channel.close();
            } catch(BindException e) {
                channel.close();
                e.printStackTrace();
                sendResult(new Exception());
            } catch (ClassNotFoundException e) {
                channel.close();
                sendResult(new Exception());
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Не удалось установить соединение");
            e.printStackTrace();
        }
    }

    private Command getCommand() throws IOException, ClassNotFoundException {
        ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
        addressOfSender = (InetSocketAddress) channel.receive(buf);
        ByteArrayInputStream readbuf = new ByteArrayInputStream(buf.array());
        ObjectInputStream readOb = new ObjectInputStream(readbuf);
        return (Command) readOb.readObject();
    }

    private void sendResult(Command result) throws IOException {
        ByteArrayOutputStream writebuf = new ByteArrayOutputStream(1024 * 1024);
        ObjectOutputStream writeOb = new ObjectOutputStream(writebuf);

        writeOb.writeObject(result);
        ByteBuffer buf = ByteBuffer.wrap(writebuf.toByteArray());
        channel.send(buf, addressOfSender);
    }

    private void runCommand(Command command) throws IOException {
        try {
            String com = command.getNameOfCommand();
            switch (com) {
                case ("info"):
                    InfoCommand info = (InfoCommand) command;
                    info.setCollection(this.collection);
                    info.setCollection(collection);
                    sendResult(info);
                    break;
                case ("show"):
                    ShowCommand show = (ShowCommand) command;
                    if (collection.getCollection() == null) throw new EmptyCollectionException();
                    if (collection.getCollection().isEmpty()) throw new EmptyCollectionException();
                    show.setCollection(collection);
                    sendResult(show);
                    break;
                case ("add"):
                    AddCommand add = (AddCommand) command;
                    collection.add(add.getPerson());
                    add.setCollection(collection);
                    sendResult(add);
                    break;
                case ("update_by_id"):
                    UpdateByIdCommand updateById = (UpdateByIdCommand) command;
                    if (collection.getCollection().isEmpty()) throw new EmptyCollectionException();
                    if (!collection.checkId(updateById.getNewId())) throw new InvalidCommandArgumentException("нет такого id");
                    collection.updateById(updateById.getNewId(), updateById.getUpdatePerson());
                    updateById.setCollection(collection);
                    sendResult(updateById);
                    break;
                case ("remove_by_id"):
                    RemoveByIdCommand removeById = (RemoveByIdCommand) command;
                    int id = removeById.getRemoveID();
                    if (collection.getCollection().isEmpty()) throw new EmptyCollectionException();
                    if (!collection.checkId(id)) throw new InvalidCommandArgumentException("нет такого id");
                    collection.removeById(id);
                    removeById.setCollection(collection);
                    sendResult(removeById);
                    break;
                case ("clear"):
                    ClearCommand clear = (ClearCommand) command;
                    collection.clear();
                    clear.setCollection(collection);
                    sendResult(clear);
                    break;
                case ("save"):
                    SaveCommand save = (SaveCommand) command;
                    System.out.println("GET");
                    if(fileWorker.getPath()==null) throw new CommandException("cannot save collection");
                    if (collection.getCollection().isEmpty()) throw new EmptyCollectionException();
                    if(!fileWorker.write(collection.serializeCollection())) throw new CommandException("cannot save collection");
                    save.setCollection(collection);
                    System.out.println("Send");
                    sendResult(save);
                    break;
                case ("execute_script"):
                    ExecuteScriptCommand executeScript = (ExecuteScriptCommand) command;
                    executeScript.setCollection(collection);
                    sendResult(executeScript);
                    break;
                case ("exit"):
                    ExitCommand exit = (ExitCommand) command;
                    //sendResult(exit);
                    break;
                case ("remove_first"):
                    RemoveFirstCommand removeFirst = new RemoveFirstCommand();
                    collection.remove(0);
                    removeFirst.setCollection(collection);
                    sendResult(removeFirst);
                    break;
                case ("reorder"):
                    ReorderCommand reorder = new ReorderCommand();
                    if (collection.getCollection().isEmpty()) throw new EmptyCollectionException();
                    collection.reorder();
                    reorder.setCollection(collection);
                    sendResult(reorder);
                    break;
                case ("history"):
                    HistoryCommand history = new HistoryCommand();
                    history.setCollection(collection);
                    sendResult(history);
                    break;
                case ("min_by_weight"):
                    MinByWeightCommand minByWeight = new MinByWeightCommand();
                    minByWeight.setMinPerson(collection.minByWeight());
                    minByWeight.setCollection(collection);
                    sendResult(minByWeight);
                    break;
                case ("group_counting_by_nationality"):
                    GroupCountingByNationalityCommand groupCountingByNationality = (GroupCountingByNationalityCommand) command;
                    groupCountingByNationality.setGroup(collection.groupByNationality(groupCountingByNationality.getNationality()));
                    groupCountingByNationality.setCollection(collection);
                    sendResult(groupCountingByNationality);
                    break;
                case ("count_by_hair_color"):
                    CountByHairColorCommand countByHairColor = (CountByHairColorCommand) command;
                    countByHairColor.setCount(collection.countByHairColor(countByHairColor.getColor()));
                    countByHairColor.setCollection(collection);
                    sendResult(countByHairColor);
                    break;
                case ("help"):
                    HelpCommand help = (HelpCommand) command;
                    help.setCollection(collection);
                    sendResult(help);
                    break;
            }
            history.add(com);
        }catch(EmptyCollectionException | InvalidCommandArgumentException e) {
            System.out.println(e.getMessage());
            Exception exception = new Exception();
            exception.setMessage(e.getMessage());
            exception.setCollection(collection);
            sendResult(exception);
        }catch(CommandException e){
            System.out.println(e.getMessage());
            Exception exception = new Exception();
            exception.setMessage(e.getMessage());
            exception.setCollection(collection);
            sendResult(exception);
        }
    }

    public class InputThread implements Runnable{
        public void run(){
            while(run) {
                CommandWrapper command = input.readCommand();
                if(command.getCom().equals("exit")){
                    run = false;
                } else{
                    System.out.println("Для того, чтобы закрыть сервер введите exit");
                }
            }
        }
    }

}
