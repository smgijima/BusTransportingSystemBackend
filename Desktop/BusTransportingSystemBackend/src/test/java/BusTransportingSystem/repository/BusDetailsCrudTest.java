package BusTransportingSystem.repository;

import BusTransportingSystem.App;
import BusTransportingSystem.domain.BusDetails;
import BusTransportingSystem.factory.BusFactory;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Siphiwo Mgijima on 8/7/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class BusDetailsCrudTest extends AbstractTestNGSpringContextTests{

    @Autowired
  private   BusRepository repository;

    @org.testng.annotations.Test
    public void testCreateBus() throws Exception {
//        repository.deleteAll();
        BusDetails busDetails = BusFactory.createBus("CA45678",30);
         BusDetails savedBusDetails =repository.save(busDetails);

        assertNotNull("CREATE TEST", busDetails);
    }

    @Test(dependsOnMethods = "testCreateBus")
    public void testReadAll() throws Exception {
        Iterable<BusDetails> buses =  repository.findAll();

        assertNotNull("READ TEST",buses);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdateAccount() throws Exception {
        BusDetails busDetailsFound = repository.findOne(2L);
        BusDetails updateBusDetails = new BusDetails.BusBuilder()
                .copy(busDetailsFound)
                .getnumberPlate("admin1245")
                .build();
        BusDetails updatedBusDetails =repository.save(updateBusDetails);
        Assert.assertEquals("UPDATE TEST", updatedBusDetails.getNumberPlate(), updateBusDetails.getNumberPlate());
    }



    @Test(dependsOnMethods = "testUpdateAccount")
    public void testDelete() throws Exception {
        BusDetails foundBusDetails = repository.findOne(3L);
        if(foundBusDetails !=null) {
            assertNotNull("BEFORE DELETE TEST", foundBusDetails);
            repository.delete(3L);
            BusDetails deletedBusDetails = repository.findOne(3L);

            assertNull("DELETE TEST", deletedBusDetails);
        }




    }
}
