package org.example.Views;

import org.example.Models.Animal;
import org.example.Presenters.AnimalPresenter;
import org.example.Presenters.View;
import org.example.Presenters.ViewObserver;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu implements View {

    //    private final AnimalDB animalsDB;
    private final Scanner scanner = new Scanner(System.in);
    private ViewObserver observer;


    public void showMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Реестр животных:");
                System.out.println("1. Завести новое животное");
                System.out.println("2. Увидеть список команд, которые выполняет животное");
                System.out.println("3. Обучить животное новым командам");
                System.out.println("4. Посмотреть список всех животных питомника");
                System.out.println("5. Посмотреть количество созданных животных");
                System.out.println("0. Выйти из реестра");
                System.out.println("Выбирете номер пункта:");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addNewAnimal();
                    case 2 -> showAnimalCommands();
                    case 3 -> teachNewCommands();
                    case 4 -> observer.showAnimals();
                    case 5 -> observer.getCountOfAnimals();
                    case 0 -> {
                        System.out.println("Реестр закрыт!");
                        return;
                    }
                    default -> System.out.println("Введен не корректный номер пункта. Введите повторно.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: неверный формат ввода. Введите повторно.");
                scanner.nextLine();
            }
        }
    }

    private void addNewAnimal() {

        System.out.println("Выбирите вид (род) животного:");
        System.out.println("1. Собака");
        System.out.println("2. Кошка");
        System.out.println("3. Хомяк");
        System.out.println("4. Лошадь");
        System.out.println("5. Верблюд");
        System.out.println("6. Осел");

        int animalType = scanner.nextInt();
        scanner.nextLine();
        if (animalType < 1 || animalType > 6) {
            System.out.println("Неверно выбран вид (род) животного.");
            return;
        }
        Animal animal;

        System.out.println("Введите имя животного:");
        String name = scanner.nextLine();
        System.out.println("Введите даты рождения животного (формат гггг-мм-дд):");
        String birthday = scanner.nextLine();
        System.out.println("Введите команды через запятую, которые умеет выполнять животное:");
        String commands = scanner.nextLine();

        observer.onAddNewAnimal(animalType, name, birthday, commands);
    }



    private void showAnimalCommands() {
        System.out.println("Введите ID интересующего животного:");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            observer.showAnimalCommands(id);
        } catch (NumberFormatException e){
            System.out.println("Введен некорректный ID");
        }
    }

    private void teachNewCommands() {
        try {
            System.out.println("Введите id животного:");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите новые команды для данного животного через запятую:");
            String commands = scanner.nextLine();

            observer.addNewCommandsToAnimalByID(id, commands);
//        System.out.println("Команды успешно добавлены!");
        } catch (NumberFormatException e){
            System.out.println("Введен некорректный ID");
        }
    }

    @Override
    public void setObserver(AnimalPresenter animalPresenter) {
        this.observer = animalPresenter;
    }

    @Override
    public void showAnimals(Collection<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    @Override
    public void printAnimalResult(String message) {
        System.out.println(message);
    }

    @Override
    public void printAnimalError(String message) {
        System.out.println(message);
    }
}

