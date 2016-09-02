package BusTransportingSystem.factory;

import BusTransportingSystem.domain.BusDetails;
import org.junit.Assert;
import org.junit.Test;



/**
 * Created by Siphiwo Mgijima on 8/7/2016.
 */
public class BusDetailsTest {
    private BusDetails busDetails;

    @Test
    public void testCreate() throws Exception {
        busDetails = BusFactory.createBus("CA123",20);
        Assert.assertNotNull(busDetails);
    }

    @Test
    public void testUpdate() throws Exception {
        busDetails = BusFactory.createBus("CA123",20);
        BusDetails copyBusDetails = new BusDetails.BusBuilder()
                .copy(busDetails)
                .getnumberPlate("CA321")
                .build();
        Assert.assertEquals(copyBusDetails.getNumberPlate(),"CA321");

    }
}
