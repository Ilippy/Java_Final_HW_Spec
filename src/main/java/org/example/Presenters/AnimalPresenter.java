package org.example.Presenters;

import org.example.Models.Animal;

import java.util.Collection;

public class AnimalPresenter implements ViewObserver {
    private final Model animalModel;
    private final View animalView;

    public AnimalPresenter(Model animalModel, View animalView) {
        this.animalModel = animalModel;
        this.animalView = animalView;
        animalView.setObserver(this);
    }

    private Collection<Animal> loadAnimals() {
        return animalModel.loadAnimals();
    }

    public void showMenu() {
        animalView.showMenu();
    }

    @Override
    public void showAnimals() {
        animalView.showAnimals(loadAnimals());
    }

    @Override
    public void showAnimalCommands(int id) {
        try {
            animalView.printAnimalResult(animalModel.getCommandsByAnimalID(id));
        } catch (RuntimeException e) {
            animalView.printAnimalError(e.getMessage());
        }
    }

    @Override
    public void addNewCommandsToAnimalByID(int id, String commands) {
        try {
            animalModel.addNewCommandsToAnimalByID(id, commands);
            animalView.printAnimalResult("Команды успешно добавлены!");
        } catch (RuntimeException e) {
            animalView.printAnimalError(e.getMessage());
        }
    }

    @Override
    public void getCountOfAnimals() {
        animalView.printAnimalResult("Общее количество животных равно " + animalModel.getCountOfAnimals());
    }


    @Override
    public void onAddNewAnimal(int animalType, String name, String birthday, String commands) {
        try {
            animalModel.addNewAnimal(animalType, name, birthday, commands);
            animalView.printAnimalResult("База данных успешно сохранена.\nЖивотное добавлено в реестр!");
        } catch (RuntimeException e) {
            animalView.printAnimalError("Не удалось добавить новое животное!\n" + e.getMessage());
        }
    }
}
