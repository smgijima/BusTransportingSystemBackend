package BusTransportingSystem.factory;

import BusTransportingSystem.domain.Traveler;
import BusTransportingSystem.domain.TravelerAddress;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cornelious on 8/7/2016.
 */
public class TravelerTest {
    private Traveler traveler;
    private TravelerAddress travelerAddress;

    @Test
    public void testCreate() throws Exception {
        travelerAddress = AddressFactory.createPassangerAddress("streetTest","CityTest","1000");
        traveler = PassengerFactory.createPassenger("admin","testlastname", travelerAddress);

        Assert.assertEquals(traveler.getObjAdress().getStreet(),"streetTest");
        Assert.assertEquals(traveler.getFirstName(),"admin");

    }

    @Test
    public void testUpdate() throws Exception {
        travelerAddress = AddressFactory.createPassangerAddress("streetTest","CityTest","1000");
        traveler = PassengerFactory.createPassenger("admin","testlastname", travelerAddress);

        Traveler copyTraveler = new Traveler.PassengerBuilder()
                .copy(traveler)
                .name("testName")
                .build();
        Assert.assertEquals(copyTraveler.getFirstName(),"testName");
        Assert.assertEquals(copyTraveler.getObjAdress().getCode(),"1000");
    }
}
