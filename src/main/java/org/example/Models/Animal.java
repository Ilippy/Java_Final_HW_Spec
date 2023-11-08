package org.example.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class Animal {
    private final int id;
    private final String name;
    private final Collection<Command> commands = new ArrayList<>();;
    private final String birthday;
    private static int count = 0;


    public Animal(String name, String birthday) {
        this.id = ++count;
        this.name = name;
        this.birthday = birthday;
    }

    public Animal(int id, String name, String birthday) {
        this.id = id;
        if (id > count)
            count = id;
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public Collection<Command> getCommands() {
        return commands;
    }

    public void newCommand(Command command) {
        this.commands.add(command);
    }

    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        String commands = this.getCommands().stream().map(Command::getCommand)
                .collect(Collectors.joining(","));
        return  this.getClass().getSimpleName() + "{" +
                "id='" + id +
                "', name='" + name +
                "', birthday='" + birthday +
                "', commands='" + commands +
                "'}";
    }
}
