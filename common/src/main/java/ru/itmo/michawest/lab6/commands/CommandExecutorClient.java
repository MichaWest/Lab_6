package ru.itmo.michawest.lab6.commands;
import ru.itmo.michawest.lab6.collection.PersonCollection;
import ru.itmo.michawest.lab6.data.Color;
import ru.itmo.michawest.lab6.data.Country;
import ru.itmo.michawest.lab6.data.Person;
import ru.itmo.michawest.lab6.exceptions.*;
import ru.itmo.michawest.lab6.inputManager.FileInput;
import ru.itmo.michawest.lab6.inputManager.InputAll;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandExecutorClient {

    static protected InetAddress addressOfServer;
    private final static int serverPort = 6027;
    private InetSocketAddress addressOfSender = null;
    private DatagramSocket client;
    private PersonCollection collection;
    private InputAll input;
    //private final FileWorker fileWorker;
    private boolean run;
    private static Stack<String> runFiles = new Stack<>();
    private final List<String> history;
    private String currentScriptFileName;
    private final String[] commands = {"help", "info", "show", "add", "remove_by_id", "update_by_id", "clear", "save", "execute_script", "exit", "remove_first", "reorder", "history", "group_counting_by_nationality", "count_by_hair_color"};


    public CommandExecutorClient(InputAll iManager) {
        this.collection = new PersonCollection();
        this.input = iManager;
        //this.fileWorker = fManager;
        history = new ArrayList<>();
    }

    public CommandExecutorClient(InputAll iManager, PersonCollection collection, DatagramSocket client) {
        this.collection = collection;
        this.input = iManager;
        this.client = client;
        //this.fileWorker = fManager;
        history = new ArrayList<>();
    }

    public void consoleMode() {
        try {
            run = true;
            addressOfServer = InetAddress.getByName("localhost");
            client = new DatagramSocket();
            while (run) {
                System.out.println("Введите команду. Чтобы получит список команд введите \"help\"");
                CommandWrapper command = input.readCommand();
                runCommand(command);
            }
            client.close();
            //}catch() {
        } catch (IOException e) {
            client.close();
            System.out.println("Не удалось установить соединение");
            e.printStackTrace();
        }
    }

    public void fileMode(String path) throws IOException {
        currentScriptFileName = path;
        input = new FileInput(path);
        run = true;
        while(run && input.getScanner().hasNextLine()){
            CommandWrapper cmd = input.readCommand();
            runCommand(cmd);
        }
    }

    //Выполняем команду
    private void runCommand(CommandWrapper command) throws IOException {
        try {
            if (!hasCommand(command.getCom())) {
                throw new NoSuchCommandException();
            }
            sendCommand(command);
            if(!command.getCom().equals("exit")) {
                getResult();
            }
        }catch(ParameterException e){
            System.out.println(e.getMessage());
        }catch (CommandException e) {
            System.out.println(e.getMessage());
        } catch (RecursiveException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    //Проверить есть ли команда
    private boolean hasCommand(String com) {
        for (String i : commands) {
            if (com.equals(i)) {
                return true;
            }
        }
        return false;
    }

    //Получит результат выполнения команды
    private void getResult() throws IOException, ClassNotFoundException {
        byte[] bufToRead = new byte[1024 * 1024];
        DatagramPacket recePacket = new DatagramPacket(bufToRead, bufToRead.length);
        client.receive(recePacket);
        ByteArrayInputStream readbuf = new ByteArrayInputStream(bufToRead);
        ObjectInputStream readOb = new ObjectInputStream(readbuf);
        Command result = (Command) readOb.readObject();
        updateData(result);
        result.getResult();
        this.collection = result.getCollection();
    }

    //Отправляем запрос на команду
    private void sendCommand(CommandWrapper command) throws IOException, ParameterException {
        ByteArrayOutputStream writebuf = new ByteArrayOutputStream(1024 * 1024);
        ObjectOutputStream writeOb = new ObjectOutputStream(writebuf);
        ByteBuffer buf;
        DatagramPacket send;
        String com = command.getCom();
        String arg = command.getArg();
        switch (com) {
            case ("help"):
                HelpCommand help = new HelpCommand();
                writeOb.writeObject(help);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("history"):
                HistoryCommand history = new HistoryCommand();
                writeOb.writeObject(history);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("info"):
                InfoCommand info = new InfoCommand();
                writeOb.writeObject(info);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("show"):
                ShowCommand show = new ShowCommand();
                writeOb.writeObject(show);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("add"):
                AddCommand add = new AddCommand();
                Person p = input.readPerson();
                add.setPerson(p);
                writeOb.writeObject(add);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                System.out.println("Отправили персона");
                writeOb.flush();
                break;
            case ("remove_by_id"):
                RemoveByIdCommand removeById = new RemoveByIdCommand();
                int id;
                if(arg==null||arg.isEmpty()){
                    throw new MissedCommandArgumentException();
                }
                try{
                    id = Integer.parseInt(arg);
                }catch(NumberFormatException e){
                    throw new InvalidCommandArgumentException("id должен быть типа int");
                }
                removeById.setRemoveID(id);
                writeOb.writeObject(removeById);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("update_by_id"):
                UpdateByIdCommand updateById = new UpdateByIdCommand();
                int nid;
                if (arg == null || arg.equals("")){
                    throw new MissedCommandArgumentException();
                }
                try{
                    nid = Integer.parseInt(arg);
                } catch (NumberFormatException e){
                    throw new InvalidCommandArgumentException("id должен быть типа int");
                }
                writeOb.writeObject(updateById);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("clear"):
                ClearCommand clear = new ClearCommand();
                writeOb.writeObject(clear);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("save"):
                SaveCommand save = new SaveCommand();
                writeOb.writeObject(save);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                System.out.println("Send");
                writeOb.flush();
                break;
            case ("execute_script"):
                ExecuteScriptCommand executeScript = new ExecuteScriptCommand();
                try {
                    if (arg == null || arg.isEmpty()) {
                        throw new MissedCommandArgumentException();
                    }
                    if (runFiles.contains(arg)) throw new RecursiveException();
                    runFiles.push(arg);
                    CommandExecutorClient process = new CommandExecutorClient(input, collection, client);
                    process.fileMode(arg);
                    runFiles.pop();
                } catch(RecursiveException e){
                    System.out.println(e.getMessage());
                }
                writeOb.writeObject(executeScript);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("remove_first"):
                RemoveFirstCommand removeFirst = new RemoveFirstCommand();
                writeOb.writeObject(removeFirst);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("exit"):
                ExitCommand exit = new ExitCommand();
                writeOb.writeObject(exit);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                run = false;
                break;
            case ("reorder"):
                ReorderCommand reorder = new ReorderCommand();
                writeOb.writeObject(reorder);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("min_by_weight"):
                MinByWeightCommand minByWeight = new MinByWeightCommand();
                writeOb.writeObject(minByWeight);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("group_counting_by_nationality"):
                GroupCountingByNationalityCommand groupCountingByNationality = new GroupCountingByNationalityCommand();
                Country nation = input.readNationality();
                groupCountingByNationality.setNationality(nation);
                writeOb.writeObject(groupCountingByNationality);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
            case ("count_by_hair_color"):
                CountByHairColorCommand countByHairColor = new CountByHairColorCommand();
                Color color = input.readHairColor();
                countByHairColor.setColor(color);
                writeOb.writeObject(countByHairColor);
                buf = ByteBuffer.wrap(writebuf.toByteArray());
                send = new DatagramPacket(buf.array(), buf.array().length, addressOfServer, serverPort);
                client.send(send);
                writeOb.flush();
                break;
        }
    }

    //Обновляем значение коллекции
    public void updateData(Command result) {
        this.collection = result.getCollection();
    }

    public void changeData(Command result){
        String com = result.getNameOfCommand();
        switch(com){
            case ("help"):
                break;
            case ("history"):
                break;
            case ("info"):
                break;
            case ("show"):
                break;
            case ("add"):
                break;
            case ("remove_by_id"):
                break;
            case ("update_by_id"):
                break;
            case ("clear"):
                break;
            case ("save"):
                break;
            case ("execute_script"):
                break;
            case ("remove_first"):
                break;
            case ("exit"):
                break;
            case ("reorder"):
                break;
            case ("min_by_weight"):
                break;
            case ("group_counting_by_nationality"):
                break;
            case ("count_by_hair_color"):
                break;
        }
    }

}
