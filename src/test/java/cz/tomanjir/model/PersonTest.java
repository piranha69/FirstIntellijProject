package cz.tomanjir.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void personTest() {
        Person person1 = new Person(1L, "Jirka");
        assertEquals(1L, person1.getId());
        assertEquals("Jirka", person1.getName());
        assertEquals("Person{id=1, name=Jirka}", new Person(1L, "Jirka").toString());

        person1.setName("Jirka2");
        assertEquals("Jirka2", person1.getName());
        assertEquals("Person{id=1, name=Jirka2}", person1.toString());

        Person person2 = new Person(2L);
        assertEquals(2L, person2.getId());
        assertNull(person2.getName());

        Person person3a = new Person(3L, "Jirka3");
        Person person3b = new Person(3L, "Jirka3");
        assertFalse(person3a==person3b);
        assertTrue(person3a.hashCode()==person3b.hashCode());
        assertTrue(person3a.equals(person3b));
    }
}