package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnimalDB {
    private final List<Animal> animals;
    private static final String FILE_PATH = "animalsdb.txt";

    public AnimalDB() {
        animals = new ArrayList<>();
        loadAnimalsDB();
    }

    public void addNewAnimal(Animal animal) {
        animals.add(animal);
        saveAnimalsDB();
    }

    public void showAnimalCommands(String name) {
        for (Animal animal : animals) {
            if(animal.getName().equals(name)) {
                animal.showAnimalCommands();
                return;
            }
        }
        System.out.println("Животное с именем " + name + " не найдено.");
    }

    public void teachNewCommads(String name, String commands) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                String newCommands = animal.getCommands() + ", " + commands;
                animal.newCommands(newCommands);
                saveAnimalsDB();
                System.out.println("Команды успешно добалены!");
                return;
            }
        }
        System.out.println("Животное с именем " + name + " не найдено.");
    }

    public void showAllAnimals() {
        try {
            File file = new File(FILE_PATH);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String animalData = fileScanner.nextLine();
                System.out.println(animalData);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл с реестром животных не найден!");
        }
    }

    public void loadAnimalsDB() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    String animalGenue = data[0];
                    String name = data[1];
                    String birthday = data[2];
                    String commands = String.join(",", Arrays.copyOfRange(data, 3, data.length));

                    Animal animal;
                    switch (animalGenue) {
                        case "Dog" -> animal = new Dog(name, birthday, commands);
                        case "Cat" -> animal = new Cat(name, birthday, commands);
                        case "Hamster" -> animal = new Hamster(name, birthday, commands);
                        case "Horse" -> animal = new Horse(name, birthday, commands);
                        case "Camel" -> animal = new Camel(name, birthday, commands);
                        case "Donkey" -> animal = new Donkey(name, birthday, commands);
                        default -> {
                            System.out.println("Неизвестный вид (род) животного: " + animalGenue);
                            continue;
                        }
                    }
                    animals.add(animal);
                } else {
                    System.out.println("Некорректные данные в файле: " + line);
                }
            }
            System.out.println("База данных успешно загружена.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении базы данных: " + e.getMessage());
        }
    }

    private void saveAnimalsDB() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Animal animal : animals) {
                String animalGenue = animal.getClass().getSimpleName();
                String name = animal.getName();
                String commands = animal.getCommands().replaceAll(",\\s+", ",");
                String birthday = animal.getBirthday();

                String line = animalGenue + "," + name + "," + birthday + "," + commands;
                writer.write(line);
                writer.newLine();
            }
            System.out.println("База данных успешно сохранена.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении базы данных: " + e.getMessage());
        }
    }
}
