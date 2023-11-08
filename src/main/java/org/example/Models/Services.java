package org.example.Models;

import java.util.Collection;

public interface Services {
    void loadAnimalsDB(Collection<Animal> animals);
    void saveAnimalToDB(Animal animal);

    void addCommandsToAnimal(Animal animal);
}
