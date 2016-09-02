package BusTransportingSystem.repository;

/**
 * Created by Siphiwo on 08/28/2016.
 */

import BusTransportingSystem.App;
import BusTransportingSystem.domain.TripDetails;
import BusTransportingSystem.factory.TripFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class TripDetailsCrudTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private TripRepository repository;

    @org.testng.annotations.Test
    public void testCreate() throws Exception {
        //repository.deleteAll();

        TripDetails tripDetails = TripFactory.createTrip("admin","test","test");
        TripDetails savedTripDetails =repository.save(tripDetails);

        assertNotNull("CREATE TEST", savedTripDetails);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testReadAll() throws Exception {
        Iterable<TripDetails> trips =  repository.findAll();

        assertNotNull("READ TEST",trips);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdate() throws Exception {
        TripDetails tripDetailsFound = repository.findOne(1L);
        TripDetails updateTripDetails = new TripDetails.TripBuilder()
                .copy(tripDetailsFound)
                .destination("testupdate")
                .build();
        TripDetails updatedTripDetails =repository.save(updateTripDetails);
        Assert.assertSame("UPDATE TEST", updateTripDetails.getDestination(), updatedTripDetails.getDestination());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        TripDetails foundTripDetails = repository.findOne(2L);
        if(foundTripDetails !=null) {
            assertNotNull("BEFORE DELETE TEST", foundTripDetails);
            repository.delete(2L);
            TripDetails deletedTripDetails = repository.findOne(2L);

            assertNull("DELETE TEST", deletedTripDetails);
        }

    }
}
