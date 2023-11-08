package org.example.Presenters;

import org.example.Models.Animal;

import java.util.Collection;

public interface View {
    void setObserver(AnimalPresenter animalPresenter);

    void showAnimals(Collection<Animal> animals);

    void printAnimalResult(String message);

    void printAnimalError(String message);

    void showMenu();
}
