package org.example.Presenters;

public interface ViewObserver {
    void onAddNewAnimal(int animalType, String name, String birthday, String commands);

    void showAnimals();

    void showAnimalCommands(int id);

    void addNewCommandsToAnimalByID(int id, String commands);

    void getCountOfAnimals();
}
