package org.example.Models.Pets;

import org.example.Models.Animal;
import org.example.Models.Command;

import java.util.Collection;

public abstract class Pet extends Animal {
    public Pet(String name, String birthday) {
        super(name, birthday);
    }

    public Pet(int id, String name, String birthday) {
        super(id, name, birthday);
    }
}
