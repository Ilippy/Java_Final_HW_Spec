package org.example.Models;

import org.example.Models.DB.AnimalDB;
import org.example.Models.PackAnimals.Camel;
import org.example.Models.PackAnimals.Donkey;
import org.example.Models.PackAnimals.Horse;
import org.example.Models.Pets.Cat;
import org.example.Models.Pets.Dog;
import org.example.Models.Pets.Hamster;
import org.example.Presenters.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class AnimalModel implements Model {
    private Collection<Animal> animals;
    private Services db;


    public AnimalModel() {
        animals = new ArrayList<>();
        db = new AnimalDB();
        db.loadAnimalsDB(animals);
    }

    public void addNewAnimal(Animal animal) {
        animals.add(animal);
        db = new AnimalDB();
        db.saveAnimalToDB(animal);
    }

    private Animal getAnimalByID(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        throw new RuntimeException("Животное с id " + id + " не найдено");
    }

    @Override
    public Collection<Animal> loadAnimals() {
        return animals;
    }

    @Override
    public void addNewAnimal(int animalType, String name, String birthday, String commands) {
        Animal animal;
        switch (animalType) {
            case 1 -> animal = new Dog(name, birthday);
            case 2 -> animal = new Cat(name, birthday);
            case 3 -> animal = new Hamster(name, birthday);
            case 4 -> animal = new Horse(name, birthday);
            case 5 -> animal = new Camel(name, birthday);
            case 6 -> animal = new Donkey(name, birthday);
            default -> {
                throw new RuntimeException("Не верно выбран тип животного");
            }
        }
        addCommandsToAnimal(commands, animal);
        animals.add(animal);
        db.saveAnimalToDB(animal);
    }

    @Override
    public String getCommandsByAnimalID(int id) {
        return getAnimalByID(id).getCommands().stream().map(Command::getCommand)
                .collect(Collectors.joining(","));
    }

    @Override
    public void addNewCommandsToAnimalByID(int id, String commands) {
        Animal animal = getAnimalByID(id);
        addCommandsToAnimal(commands, animal);
        db.addCommandsToAnimal(animal);
    }

    @Override
    public int getCountOfAnimals() {
        return Animal.getCount();
    }

    private void addCommandsToAnimal(String commands, Animal animal){
        for (String command : commands.split(",")) {
            animal.newCommand(new Command(command.trim()));
        }
    }
}
