package cz.tomanjir;

import cz.tomanjir.model.Animal;
import cz.tomanjir.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String... args) {
        LOG.info("App started.");

        Person person = new Person(1, "Jirka");
        LOG.info("Created person={}", person);

        Animal animal = new Animal(1, "Mirka");
        LOG.info("Created animal={}", animal);
    }
}