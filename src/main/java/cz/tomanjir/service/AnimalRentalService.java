package cz.tomanjir.service;

import cz.tomanjir.model.Animal;

interface AnimalRentalService extends Service {
    boolean registerAnimal(Animal animal);

    boolean unregisterAnimal(Animal animal);

    void unregisterAllAnimals();

    int getNumberOfRegisteredAnimals();
}