package org.example.Models;

public class Command {
    private static int counter = 9000;
    private final int id;
    private String command;

    {
        this.id = ++counter;
    }

    public Command(String command) {
        this.command = command;
    }

    public int getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return this.command;
    }
}
