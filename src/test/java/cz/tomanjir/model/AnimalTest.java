package cz.tomanjir.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {

    @Test
    public void animalTest() {
        Animal animal1 = new Animal(1L, "animal1");
        assertEquals(1L, animal1.getId());
        assertEquals("animal1", animal1.getName());
        assertEquals("Animal{id=2, name=animal1}", new Animal(1L, "animal1").toString());

        animal1.setName("animal2");
        assertEquals("animal2", animal1.getName());
        assertEquals("Animal{id=1, name=animal2}", animal1.toString());

        Animal animal2 = new Animal(2L);
        assertEquals(2L, animal2.getId());
        assertNull(animal2.getName());

        Animal animal3a = new Animal(3L, "animal3");
        Animal animal3b = new Animal(3L, "animal3");
        assertFalse(animal3a == animal3b);
        assertTrue(animal3a.hashCode() == animal3b.hashCode());
        assertTrue(animal3a.equals(animal3b));
    }
}