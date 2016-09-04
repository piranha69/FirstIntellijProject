package cz.tomanjir.service;

import cz.tomanjir.model.Animal;

public interface AnimalRentalService extends Service {
    public abstract boolean registerAnimal(Animal animal);

    public abstract boolean unregisterAnimal(Animal animal);

    public abstract void unregisterAllAnimals();

    public abstract int getNumberOfRegisteredAnimals();
}