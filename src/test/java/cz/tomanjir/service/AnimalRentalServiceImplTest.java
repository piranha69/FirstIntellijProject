package cz.tomanjir.service;

import cz.tomanjir.model.Animal;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalRentalServiceImplTest {

    @Before
    public void before() {
        AnimalRentalServiceImpl.getInstance().unregisterAllAnimals();
    }

    @Test
    public void getInstance() throws Exception {
        assertNotNull(AnimalRentalServiceImpl.getInstance());
    }

    @Test
    public void registerAnimal() throws Exception {
        Animal animal = new Animal(1L);
        assertTrue(AnimalRentalServiceImpl.getInstance().registerAnimal(animal));
        assertFalse(AnimalRentalServiceImpl.getInstance().registerAnimal(animal));
        assertFalse(AnimalRentalServiceImpl.getInstance().registerAnimal(null));
    }

    @Test
    public void unregisterAnimal() throws Exception {
        Animal animal = new Animal(1L);
        assertFalse(AnimalRentalServiceImpl.getInstance().unregisterAnimal(animal));
        AnimalRentalServiceImpl.getInstance().registerAnimal(animal);
        assertTrue(AnimalRentalServiceImpl.getInstance().unregisterAnimal(animal));

        assertFalse(AnimalRentalServiceImpl.getInstance().unregisterAnimal(null));
        AnimalRentalServiceImpl.getInstance().registerAnimal(null);
        assertFalse(AnimalRentalServiceImpl.getInstance().unregisterAnimal(null));
    }

    @Test
    public void unregisterAllAnimals() throws Exception {
        AnimalRentalServiceImpl.getInstance().registerAnimal(new Animal(1L));
        AnimalRentalServiceImpl.getInstance().registerAnimal(new Animal(2L));
        assertTrue(AnimalRentalServiceImpl.getInstance().getNumberOfRegisteredAnimals() == 2);
        AnimalRentalServiceImpl.getInstance().unregisterAllAnimals();
        assertTrue(AnimalRentalServiceImpl.getInstance().getNumberOfRegisteredAnimals() == 0);
    }

    @Test
    public void getNumberOfRegisteredAnimals() throws Exception {
        AnimalRentalServiceImpl.getInstance().registerAnimal(new Animal(1L));
        assertTrue(AnimalRentalServiceImpl.getInstance().getNumberOfRegisteredAnimals() == 1);

        AnimalRentalServiceImpl.getInstance().registerAnimal(new Animal(2L));
        assertTrue(AnimalRentalServiceImpl.getInstance().getNumberOfRegisteredAnimals() == 2);

        AnimalRentalServiceImpl.getInstance().unregisterAllAnimals();
        assertTrue(AnimalRentalServiceImpl.getInstance().getNumberOfRegisteredAnimals() == 0);
    }

    @Test
    public void close() throws Exception {
        AnimalRentalServiceImpl.getInstance().close();
        assertTrue(AnimalRentalServiceImpl.getInstance().getNumberOfRegisteredAnimals() == 0);
    }
}