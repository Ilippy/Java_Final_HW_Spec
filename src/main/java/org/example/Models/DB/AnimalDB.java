package org.example.Models.DB;

import org.example.Models.*;
import org.example.Models.PackAnimals.Camel;
import org.example.Models.PackAnimals.Donkey;
import org.example.Models.PackAnimals.Horse;
import org.example.Models.Pets.Cat;
import org.example.Models.Pets.Dog;
import org.example.Models.Pets.Hamster;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AnimalDB implements Services {

    private static final String FILE_PATH = "animalsdb.txt";


    public void loadAnimalsDB(Collection<Animal> animals) {
//        Collection<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int size = data.length;
                if (size > 3) {
                    int id = Integer.parseInt(data[0]);
                    String animalGenue = data[1];
                    String name = data[2];
                    String birthday = data[3];

//                    String commands = String.join(",", Arrays.copyOfRange(data, 3, data.length));

                    Animal animal;
                    switch (animalGenue) {
                        case "Dog" -> animal = new Dog(id, name, birthday);
                        case "Cat" -> animal = new Cat(id, name, birthday);
                        case "Hamster" -> animal = new Hamster(id, name, birthday);
                        case "Horse" -> animal = new Horse(id, name, birthday);
                        case "Camel" -> animal = new Camel(id, name, birthday);
                        case "Donkey" -> animal = new Donkey(id, name, birthday);
                        default -> {
                            System.out.println("Неизвестный вид (род) животного: " + animalGenue);
                            continue;
                        }
                    }
                    for (int i = 3; i < size; i++) {
                        animal.newCommand(new Command(data[i]));
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
//        return animals;
    }

    public void saveAnimalToDB(Animal animal) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            int id = animal.getId();
            String animalType = animal.getClass().getSimpleName();
            String name = animal.getName();
            String commands = animal.getCommands().stream().map(Command::getCommand)
                    .collect(Collectors.joining(","))
                    .replaceAll(",\\s+", ",");

            String birthday = animal.getBirthday();

            String line = id + animalType + "," + name + "," + birthday + "," + commands;
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении базы данных: " + e.getMessage());
        }
    }

    @Override
    public void addCommandsToAnimal(Animal animal) {
        String fileText = getEditedFile(animal);
//        System.out.println(fileText);
        saveDBByString(fileText);
    }

    private String getEditedFile(Animal animal){
        StringBuilder fileText = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if(data[0] != null && data[0].equals(String.valueOf(animal.getId()))){
                    int id = animal.getId();
                    String animalType = animal.getClass().getSimpleName();
                    String name = animal.getName();
                    String commands = animal.getCommands().stream().map(Command::getCommand)
                            .collect(Collectors.joining(","));
                    String birthday = animal.getBirthday();
                    line = id + "," + animalType + "," + name + "," + birthday + "," + commands;
                }
//                System.out.println(line);
                fileText.append(line).append(System.lineSeparator());

            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении базы данных: " + e.getMessage());
        }
        return fileText.toString();
    }

    private void saveDBByString(String fileText){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(fileText);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении базы данных: " + e.getMessage());
        }
    }


}
