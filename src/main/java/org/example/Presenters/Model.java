package org.example.Presenters;

import org.example.Models.Animal;

import java.util.Collection;

public interface Model {
    Collection<Animal> loadAnimals();

    void addNewAnimal(int animalType, String name, String birthday, String commands);

    String getCommandsByAnimalID(int id);

    void addNewCommandsToAnimalByID(int id, String commands);

    int getCountOfAnimals();
}
