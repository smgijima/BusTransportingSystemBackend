package BusTransportingSystem.api;

import BusTransportingSystem.App;
import BusTransportingSystem.domain.BusDetails;
import BusTransportingSystem.factory.BusFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * Created by Siphiwo on 08/28/2016.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration(classes = App.class)

@WebAppConfiguration
public class BusDetailsControllerTest extends AbstractTestNGSpringContextTests {


    @Test
    public void testCreate(){
        String URI =  "http://localhost:8080/bu";
        RestTemplate restTemplate = new RestTemplate();
        BusDetails busDetails = BusFactory.createBus("testApi",20);
        restTemplate.postForObject(URI, busDetails, BusDetails.class );
    }
    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/bu/{id}";
        RestTemplate restTemplate = new RestTemplate();
        BusDetails busDetails = restTemplate.getForObject(URI, BusDetails.class, "7");
        Assert.assertNotNull(busDetails);
        Assert.assertEquals(0, busDetails.getSeats());

        Assert.assertEquals("CA45678", busDetails.getNumberPlate());
    }
    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/bu/{id}";
        RestTemplate restTemplate = new RestTemplate();
        BusDetails busDetails = restTemplate.getForObject(URI, BusDetails.class, "23");
        if(busDetails !=null) {
            String UPDATE_URI = "http://localhost:8080/busDetails";
            BusDetails updateBusDetails = new BusDetails.BusBuilder()
                    .copy(busDetails)
                    .getnumberPlate("successApi")
                    .build();
            restTemplate.put(UPDATE_URI, updateBusDetails);
            BusDetails updatedBusDetails = restTemplate.getForObject(URI, BusDetails.class, "23");

            Assert.assertNotEquals(updatedBusDetails.getNumberPlate(), busDetails.getNumberPlate());
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/bu";
        RestTemplate restTemplate = new RestTemplate();
        Set busSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(busSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/bu/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"28");
        BusDetails busDetails = restTemplate.getForObject(URI, BusDetails.class, "28");

        Assert.assertNull(busDetails);


    }

}
