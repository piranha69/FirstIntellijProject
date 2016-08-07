package cz.tomanjir.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void toStringTest() {
        Person person = new Person(1L, "Jirka");
        assertEquals("Person{id=1, name='Jirka'}", person.toString());
    }
}