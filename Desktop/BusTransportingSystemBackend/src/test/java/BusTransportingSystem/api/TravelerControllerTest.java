package BusTransportingSystem.api;

import BusTransportingSystem.domain.Traveler;
import BusTransportingSystem.domain.TravelerAddress;
import BusTransportingSystem.factory.AddressFactory;
import BusTransportingSystem.factory.PassengerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * Created by Siphiwo on 08/28/2016.
 */
public class TravelerControllerTest {
    @Test
    public void testCreate(){
        String URI =  "http://localhost:8080/pass";
        RestTemplate restTemplate = new RestTemplate();
        TravelerAddress travelerAddress = AddressFactory.createPassangerAddress("testApi","admin","1000");
        Traveler traveler = PassengerFactory.createPassenger("testApi","test", travelerAddress);
        restTemplate.postForObject(URI, traveler, Traveler.class );
    }
    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/pass/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Traveler traveler = restTemplate.getForObject(URI, Traveler.class, "6");
        Assert.assertNotNull(traveler);
        Assert.assertEquals("testApi", traveler.getFirstName());

        Assert.assertEquals("test", traveler.getLastName());
    }
    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/pass/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Traveler traveler = restTemplate.getForObject(URI, Traveler.class, "4");
        if(traveler !=null) {
            String UPDATE_URI = "http://localhost:8080/pass";
            Traveler updateTraveler = new Traveler.PassengerBuilder()
                    .copy(traveler)
                    .name("successApi")
                    .build();
            restTemplate.put(UPDATE_URI, updateTraveler);
            Traveler updatedTraveler = restTemplate.getForObject(URI, Traveler.class, "4");

            Assert.assertEquals(updateTraveler.getFirstName(), updatedTraveler.getFirstName());
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/pass";
        RestTemplate restTemplate = new RestTemplate();
        Set employeeSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(employeeSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/pass/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"1");
        Traveler traveler = restTemplate.getForObject(URI, Traveler.class, "1");

        Assert.assertNull(traveler);


    }
}
