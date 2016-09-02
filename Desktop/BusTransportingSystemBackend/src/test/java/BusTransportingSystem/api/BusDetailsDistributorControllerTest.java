package BusTransportingSystem.api;

import BusTransportingSystem.domain.BusDistributor;
import BusTransportingSystem.factory.BusSupplierFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * Created by Siphiwo on 08/28/2016.
 */
public class BusDetailsDistributorControllerTest {
    @Test
    public void testCreate(){
        String URI =  "http://localhost:8080/bu";
        RestTemplate restTemplate = new RestTemplate();
        BusDistributor busDistributor = BusSupplierFactory.createSupplier("testApi","test");
        restTemplate.postForObject(URI, busDistributor, BusDistributor.class );
    }
    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/bussup/{id}";
        RestTemplate restTemplate = new RestTemplate();
        BusDistributor busDistributor = restTemplate.getForObject(URI, BusDistributor.class, "7");
        Assert.assertNotNull(busDistributor);
        Assert.assertEquals("1234567890", busDistributor.getContactNumber());

        Assert.assertEquals("serviceTest", busDistributor.getName());
    }
    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/bussup/{id}";
        RestTemplate restTemplate = new RestTemplate();
        BusDistributor busDistributor = restTemplate.getForObject(URI, BusDistributor.class, "5");
        if(busDistributor !=null) {
            String UPDATE_URI = "http://localhost:8080/bussup";
            BusDistributor updateSupplier = new BusDistributor.SupplierBuilder()
                    .copy(busDistributor)
                    .name("successApi")
                    .build();
            restTemplate.put(UPDATE_URI,updateSupplier);
            BusDistributor updatedBus= restTemplate.getForObject(URI, BusDistributor.class, "5");

            Assert.assertNotEquals(updatedBus.getName(), busDistributor.getName());
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/bussup";
        RestTemplate restTemplate = new RestTemplate();
        Set supplierSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(supplierSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/bussup/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"6");
        BusDistributor busDistributor = restTemplate.getForObject(URI, BusDistributor.class, "6");

        Assert.assertNull(busDistributor);


    }

}
