package cz.tomanjir;

import cz.tomanjir.model.Animal;
import cz.tomanjir.service.AnimalRentalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {

    private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String... args) {
        LOG.info("MainApp started.");

        init();
        close();

        LOG.info("MainApp finished.");
    }

    private static void init() {
        LOG.info("MainApp initializing..");
        AnimalRentalServiceImpl.getInstance().registerAnimal(new Animal(1L, "dog1"));
        AnimalRentalServiceImpl.getInstance().registerAnimal(new Animal(2L, "dog2"));
        AnimalRentalServiceImpl.getInstance().registerAnimal(new Animal(3L, "dog3"));
        AnimalRentalServiceImpl.getInstance().registerAnimal(new Animal(4L, "dog4"));
        LOG.info("MainApp initialized.");
    }

    private static void close() {
        LOG.info("MainApp closing..");
        AnimalRentalServiceImpl.getInstance().close();
        LOG.info("MainApp closed.");
    }
}