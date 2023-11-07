package org.example;

public abstract class Animal {
    private final String name;
    private String commands;
    private final String birthday;
    private static int count = 0;

    {
        count++;
    }

    public Animal(String name, String birthday, String commands) {
        this.name = name;
        this.birthday = birthday;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCommands() {
        return commands;
    }

    public void newCommands(String newCommands) {
        this.commands = newCommands;
    }

    public void showAnimalCommands() {
        System.out.println(commands);
    }

    public static int getCount() {
        return count;
    }
}
