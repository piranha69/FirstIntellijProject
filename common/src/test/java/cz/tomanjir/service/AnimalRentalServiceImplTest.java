package cz.tomanjir.service;

import cz.tomanjir.model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalRentalServiceImplTest {

    private AnimalRentalServiceImpl animalRentalService;

    @BeforeEach
    public void before() {
        animalRentalService = new AnimalRentalServiceImpl();
    }

    @Test
    public void registerAnimal() throws Exception {
        Animal animal = new Animal(1L);
        assertTrue(animalRentalService.registerAnimal(animal));
        assertFalse(animalRentalService.registerAnimal(animal));
        assertFalse(animalRentalService.registerAnimal(null));
    }

    @Test
    public void unregisterAnimal() throws Exception {
        Animal animal = new Animal(1L);
        assertFalse(animalRentalService.unregisterAnimal(animal));
        animalRentalService.registerAnimal(animal);
        assertTrue(animalRentalService.unregisterAnimal(animal));

        assertFalse(animalRentalService.unregisterAnimal(null));
        animalRentalService.registerAnimal(null);
        assertFalse(animalRentalService.unregisterAnimal(null));
    }

    @Test
    public void unregisterAllAnimals() throws Exception {
        animalRentalService.registerAnimal(new Animal(1L));
        animalRentalService.registerAnimal(new Animal(2L));
        assertTrue(animalRentalService.getNumberOfRegisteredAnimals() == 2);
        animalRentalService.unregisterAllAnimals();
        assertTrue(animalRentalService.getNumberOfRegisteredAnimals() == 0);
    }

    @Test
    public void getNumberOfRegisteredAnimals() throws Exception {
        animalRentalService.registerAnimal(new Animal(1L));
        assertTrue(animalRentalService.getNumberOfRegisteredAnimals() == 1);

        animalRentalService.registerAnimal(new Animal(2L));
        assertTrue(animalRentalService.getNumberOfRegisteredAnimals() == 2);

        animalRentalService.unregisterAllAnimals();
        assertTrue(animalRentalService.getNumberOfRegisteredAnimals() == 0);
    }

    @Test
    public void close() throws Exception {
        animalRentalService.destroyService();
        assertTrue(animalRentalService.getNumberOfRegisteredAnimals() == 0);
    }
}