package cz.tomanjir.service;

import cz.tomanjir.model.Animal;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalRentalServiceTest {

    @Before
    public void before() {
        AnimalRentalService.getInstance().unregisterAllAnimals();
    }

    @Test
    public void getInstance() throws Exception {
        assertNotNull(AnimalRentalService.getInstance());
    }

    @Test
    public void registerAnimal() throws Exception {
        Animal animal = new Animal(1L);
        assertTrue(AnimalRentalService.getInstance().registerAnimal(animal));
        assertFalse(AnimalRentalService.getInstance().registerAnimal(animal));
        assertFalse(AnimalRentalService.getInstance().registerAnimal(null));
    }

    @Test
    public void unregisterAnimal() throws Exception {
        Animal animal = new Animal(1L);
        assertFalse(AnimalRentalService.getInstance().unregisterAnimal(animal));
        AnimalRentalService.getInstance().registerAnimal(animal);
        assertTrue(AnimalRentalService.getInstance().unregisterAnimal(animal));

        assertFalse(AnimalRentalService.getInstance().unregisterAnimal(null));
        AnimalRentalService.getInstance().registerAnimal(null);
        assertFalse(AnimalRentalService.getInstance().unregisterAnimal(null));
    }

    @Test
    public void unregisterAllAnimals() throws Exception {
        AnimalRentalService.getInstance().registerAnimal(new Animal(1L));
        AnimalRentalService.getInstance().registerAnimal(new Animal(2L));
        assertTrue(AnimalRentalService.getInstance().getNumberOfRegisteredAnimals()==2);
        AnimalRentalService.getInstance().unregisterAllAnimals();
        assertTrue(AnimalRentalService.getInstance().getNumberOfRegisteredAnimals()==0);
    }

    @Test
    public void getNumberOfRegisteredAnimals() throws Exception {
        AnimalRentalService.getInstance().registerAnimal(new Animal(1L));
        assertTrue(AnimalRentalService.getInstance().getNumberOfRegisteredAnimals()==1);

        AnimalRentalService.getInstance().registerAnimal(new Animal(2L));
        assertTrue(AnimalRentalService.getInstance().getNumberOfRegisteredAnimals()==2);

        AnimalRentalService.getInstance().unregisterAllAnimals();
        assertTrue(AnimalRentalService.getInstance().getNumberOfRegisteredAnimals()==0);
    }

    @Test
    public void close() throws Exception {
        AnimalRentalService.getInstance().close();
        assertTrue(AnimalRentalService.getInstance().getNumberOfRegisteredAnimals()==0);
    }
}