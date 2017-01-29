package cz.tomanjir.service;

import com.google.common.base.Optional;
import cz.tomanjir.model.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuavaService {

    private static final Logger LOG = LoggerFactory.getLogger(GuavaService.class);

    private static final Animal NULL_ANIMAL = new Animal(0L);

    public static void main(String... args) {
        Optional<Animal> animal = Optional.fromNullable(new Animal(1L, "Mirka"));
        LOG.info("{}", animal.or(NULL_ANIMAL));

        animal = Optional.fromNullable(null);
        LOG.info("{}", animal.or(NULL_ANIMAL));
    }
}