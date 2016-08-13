package cz.tomanjir.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void animalTest() {
        Animal animal1 = new Animal(1L, "Mirka");
        assertEquals(1L, animal1.getId());
        assertEquals("Mirka", animal1.getName());
        assertEquals("Animal{id=1, name=Mirka}", new Animal(1L, "Mirka").toString());

        animal1.setName("Mirka2");
        assertEquals("Mirka2", animal1.getName());
        assertEquals("Animal{id=1, name=Mirka2}", animal1.toString());

        Animal animal2 = new Animal(2L);
        assertEquals(2L, animal2.getId());
        assertNull(animal2.getName());

        Animal animal3a = new Animal(3L, "Mirka3");
        Animal animal3b = new Animal(3L, "Mirka3");
        assertFalse(animal3a==animal3b);
        assertTrue(animal3a.hashCode()==animal3b.hashCode());
        assertTrue(animal3a.equals(animal3b));
    }
}