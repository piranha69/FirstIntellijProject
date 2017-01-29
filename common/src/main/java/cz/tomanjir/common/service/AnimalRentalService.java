package cz.tomanjir.common.service;

import cz.tomanjir.common.model.Animal;

interface AnimalRentalService extends Service {
    boolean registerAnimal(Animal animal);

    boolean unregisterAnimal(Animal animal);

    void unregisterAllAnimals();

    int getNumberOfRegisteredAnimals();
}