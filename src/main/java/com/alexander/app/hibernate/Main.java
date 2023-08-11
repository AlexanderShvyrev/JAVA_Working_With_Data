package com.alexander.app.hibernate;

import com.alexander.app.hibernate.entity.Animal;

public class Main {
    public static void main(String[] args) {

        AnimalHelper ah = new AnimalHelper();

        Animal animal = new Animal(2, "Lion", true);

        ah.addAnimal(animal);
    }
}
