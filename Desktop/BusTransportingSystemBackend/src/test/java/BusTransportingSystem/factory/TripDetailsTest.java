package BusTransportingSystem.factory;

import BusTransportingSystem.domain.TripDetails;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cornelious on 8/7/2016.
 */
public class TripDetailsTest {
    private TripDetails tripDetails;

    @Test
    public void testCreate() throws Exception {
        tripDetails = TripFactory.createTrip("CPT","12hrs","JHB");

        Assert.assertEquals(tripDetails.getDestination(),"JHB");
        Assert.assertEquals(tripDetails.getDeparture(),"CPT");
        Assert.assertEquals(tripDetails.getTime(),"12hrs");
    }

    @Test
    public void testUpdate() throws Exception {
        tripDetails = TripFactory.createTrip("CPT","12hrs","JHB");

        TripDetails copyTripDetails = new TripDetails.TripBuilder()
                .copy(tripDetails)
                .time("10hrs")
                .build();
        Assert.assertEquals(copyTripDetails.getDeparture(),"CPT");
        Assert.assertEquals(copyTripDetails.getTime(),"10hrs");

    }
}
