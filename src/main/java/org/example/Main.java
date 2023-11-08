package org.example;

import org.example.Models.AnimalModel;
import org.example.Presenters.AnimalPresenter;
import org.example.Views.Menu;

public class Main {
    public static void main(String[] args) {
        AnimalPresenter animalPresenter = new AnimalPresenter(new AnimalModel(), new Menu());
        animalPresenter.showMenu();
    }
}