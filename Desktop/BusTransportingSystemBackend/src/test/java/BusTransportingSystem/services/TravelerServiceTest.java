package BusTransportingSystem.services;
import BusTransportingSystem.App;
import BusTransportingSystem.domain.Traveler;
import BusTransportingSystem.domain.TravelerAddress;
import BusTransportingSystem.factory.AddressFactory;
import BusTransportingSystem.factory.PassengerFactory;
import BusTransportingSystem.services.impl.PassengerService;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.testng.annotations.Test;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
/**
 * Created by Cornelious on 8/7/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class TravelerServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
     private  PassengerService service;

    @Test
    public void testCreate() throws Exception {
        //repository.deleteAll();
        TravelerAddress employeeAddress = AddressFactory.createPassangerAddress("admin","test","0000");
        Traveler traveler = PassengerFactory.createPassenger("admin","test",employeeAddress);
        Traveler savedTraveler =service.create(traveler);

        assertNotNull("CREATE TEST", savedTraveler);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testReadAll() throws Exception {
        Iterable<Traveler> passengers =  service.readAll();

        assertNotNull("READ TEST",passengers);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdate() throws Exception {
        Traveler travelerFound = service.readById(1L);
        Traveler updateTraveler = new Traveler.PassengerBuilder()
                .copy(travelerFound)
                .name("service testupdate")
                .build();
        Traveler updatedTraveler =service.update(updateTraveler);
        Assert.assertSame("UPDATE TEST", updateTraveler.getFirstName(), updatedTraveler.getFirstName());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Traveler foundPassemger = service.readById(3L);
        if(foundPassemger !=null) {
            assertNotNull("BEFORE DELETE TEST",foundPassemger);
            service.delete(foundPassemger);
            Traveler deletedpassenger = service.readById(3L);

            assertNull("DELETE TEST",deletedpassenger);
        }

    }
}
