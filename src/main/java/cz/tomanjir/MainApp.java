package cz.tomanjir;

import cz.tomanjir.model.Animal;
import cz.tomanjir.model.Person;
import cz.tomanjir.service.AnimalRentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String... args) {
        LOG.info("MainApp started.");

        init();

        LOG.info("MainApp finished.");
    }

    private static void init() {
        LOG.info("MainApp Initializing..");
        AnimalRentalService.getInstance().registerAnimal(new Animal(1L, "dog1"));
        AnimalRentalService.getInstance().registerAnimal(new Animal(2L, "dog2"));
        AnimalRentalService.getInstance().registerAnimal(new Animal(3L, "dog3"));
        AnimalRentalService.getInstance().registerAnimal(new Animal(4L, "dog4"));
        AnimalRentalService.getInstance().registerAnimal(new Animal(4L, "dog4"));
        LOG.info("MainApp Initialized.");
    }
}