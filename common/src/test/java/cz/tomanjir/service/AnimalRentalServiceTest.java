package cz.tomanjir.service;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnimalRentalServiceTest {

    @Test
    public void test() {
        AnimalRentalService mock = EasyMock.niceMock(AnimalRentalService.class);
        EasyMock.replay(mock);
        assertThat(mock.getNumberOfRegisteredAnimals(), is(0));

        EasyMock.reset(mock);
        EasyMock.expect(mock.getNumberOfRegisteredAnimals()).andReturn(1).anyTimes();
        EasyMock.replay(mock);

        assertThat(mock.getNumberOfRegisteredAnimals(), is(1));
        assertThat(mock.getNumberOfRegisteredAnimals(), is(1));
    }
}