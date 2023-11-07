package org.example;

public class Main {
    public static void main(String[] args) {
        AnimalDB animalsDB = new AnimalDB();
        Menu menu = new Menu(animalsDB);
        menu.showMenu();
    }
}