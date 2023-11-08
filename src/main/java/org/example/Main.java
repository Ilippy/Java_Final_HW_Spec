package org.example;

import org.example.Models.AnimalModel;
import org.example.Presenters.AnimalPresenter;
import org.example.Presenters.Model;
import org.example.Presenters.View;
import org.example.Views.Menu;

public class Main {
    public static void main(String[] args) {
        Model model = new AnimalModel();
        View view = new Menu();
        AnimalPresenter animalPresenter = new AnimalPresenter(model, view);
        animalPresenter.showMenu();
    }
}