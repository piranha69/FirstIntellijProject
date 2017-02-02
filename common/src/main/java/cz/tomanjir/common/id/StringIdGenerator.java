package cz.tomanjir.common.id;

import javax.inject.Inject;
import java.util.concurrent.atomic.AtomicLong;

public class StringIdGenerator implements IdGenerator<String> {

    private final String prefix;
    private final AtomicLong idGenerator;

    @Inject
    public StringIdGenerator(String prefix) {
        this.prefix = prefix;
        this.idGenerator = new AtomicLong(System.currentTimeMillis());
    }

    @Override
    public String nextId() {
        return prefix + idGenerator.getAndIncrement();
    }
}
