package cz.tomanjir.common.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonTest {

    @Test
    public void personTest() {
        Person person1 = new Person(1L, "person");
        assertEquals(1L, person1.getId());
        assertEquals("person", person1.getName());
        assertEquals("Person{id=1, name=person}", new Person(1L, "person").toString());

        person1.setName("person2");
        assertEquals("person2", person1.getName());
        assertEquals("Person{id=1, name=person2}", person1.toString());

        Person person2 = new Person(2L);
        assertEquals(2L, person2.getId());
        assertNull(person2.getName());

        Person person3a = new Person(3L, "person3");
        Person person3b = new Person(3L, "person3");
        assertFalse(person3a == person3b);
        assertTrue(person3a.hashCode() == person3b.hashCode());
        assertTrue(person3a.equals(person3b));
    }
}