package org.example.Models.PackAnimals;

import org.example.Models.Animal;

public abstract class PackAnimal extends Animal {
    public PackAnimal(String name, String birthday) {
        super(name, birthday);
    }

    public PackAnimal(int id, String name, String birthday) {
        super(id, name, birthday);
    }
}
