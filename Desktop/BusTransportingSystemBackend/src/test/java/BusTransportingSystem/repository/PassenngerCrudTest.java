package BusTransportingSystem.repository;

import BusTransportingSystem.domain.Traveler;
import BusTransportingSystem.domain.TravelerAddress;
import BusTransportingSystem.factory.AddressFactory;
import BusTransportingSystem.factory.PassengerFactory;
import BusTransportingSystem.App;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Siphiwo on 08/28/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class PassenngerCrudTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private  PassengerRepository repository;

    @org.testng.annotations.Test
    public void testCreate() throws Exception {
        //repository.deleteAll();
        TravelerAddress employeeAddress = AddressFactory.createPassangerAddress("admin","test","0000");
        Traveler traveler = PassengerFactory.createPassenger("admin","test",employeeAddress);
        Traveler savedTraveler =repository.save(traveler);

        assertNotNull("CREATE TEST", savedTraveler);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testReadAll() throws Exception {
        Iterable<Traveler> passengers =  repository.findAll();

        assertNotNull("READ TEST",passengers);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdate() throws Exception {
        Traveler travelerFound = repository.findOne(1L);
        Traveler updateTraveler = new Traveler.PassengerBuilder()
                .copy(travelerFound)
                .name("testupdate")
                .build();
        Traveler updatedTraveler =repository.save(updateTraveler);
        Assert.assertSame("UPDATE TEST", updateTraveler.getFirstName(), updatedTraveler.getFirstName());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Traveler foundPassemger = repository.findOne(2L);
        if(foundPassemger !=null) {
            assertNotNull("BEFORE DELETE TEST",foundPassemger);
            repository.delete(2L);
            Traveler deletedpassenger = repository.findOne(2L);

            assertNull("DELETE TEST",deletedpassenger);
        }

    }
}
