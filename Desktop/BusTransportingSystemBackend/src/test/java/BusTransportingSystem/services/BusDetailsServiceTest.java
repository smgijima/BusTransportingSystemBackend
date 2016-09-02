package BusTransportingSystem.services;

import BusTransportingSystem.App;
import BusTransportingSystem.domain.BusDetails;
import BusTransportingSystem.factory.BusFactory;

import BusTransportingSystem.services.impl.BusService;
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
public class BusDetailsServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private   BusService service;

    @Test
    public void testCreateBus() throws Exception {
//        repository.deleteAll();
        BusDetails busDetails = BusFactory.createBus("CA45678",30);
        BusDetails savedBusDetails =service.create(busDetails);

        assertNotNull("CREATE TEST", busDetails);
    }

    @Test(dependsOnMethods = "testCreateBus")
    public void testReadAll() throws Exception {
        Iterable<BusDetails> buses =  service.readAll();

        assertNotNull("READ TEST",buses);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdateAccount() throws Exception {
        BusDetails busDetailsFound = service.readById(2L);
        BusDetails updateBusDetails = new BusDetails.BusBuilder()
                .copy(busDetailsFound)
                .getnumberPlate("service1245")
                .build();
        BusDetails updatedBusDetails =service.update(updateBusDetails);
        Assert.assertEquals("UPDATE TEST", updatedBusDetails.getNumberPlate(), updateBusDetails.getNumberPlate());
    }

    @Test(dependsOnMethods = "testUpdateAccount")
    public void testDelete() throws Exception {
        BusDetails foundBusDetails = service.readById(3L);
        if(foundBusDetails !=null) {
            assertNotNull("BEFORE DELETE TEST", foundBusDetails);
            service.delete(foundBusDetails);
            BusDetails deletedBusDetails = service.readById(3L);

            assertNull("DELETE TEST", deletedBusDetails);
        }

    }
}
