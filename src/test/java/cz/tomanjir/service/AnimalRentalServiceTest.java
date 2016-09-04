package cz.tomanjir.service;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class AnimalRentalServiceTest {

    @Test
    public void test() {
        AnimalRentalService mock = EasyMock.niceMock(AnimalRentalService.class);
        EasyMock.replay(mock);
        Assert.assertThat(mock.getNumberOfRegisteredAnimals(), is(0));

        EasyMock.reset(mock);
        EasyMock.expect(mock.getNumberOfRegisteredAnimals()).andReturn(1).anyTimes();
        EasyMock.replay(mock);

        Assert.assertThat(mock.getNumberOfRegisteredAnimals(), is(1));
        Assert.assertThat(mock.getNumberOfRegisteredAnimals(), is(1));
    }
}