package BusTransportingSystem.api;

import BusTransportingSystem.domain.EmpAdress;
import BusTransportingSystem.domain.EmployeeDetails;
import BusTransportingSystem.factory.AddressFactory;
import BusTransportingSystem.factory.EmployeeFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * Created by Siphiwo on 08/28/2016.
 */
public class EmployeeControllerTest {
    @Test
    public void testCreate(){
        String URI =  "http://localhost:8080/emp";
        RestTemplate restTemplate = new RestTemplate();
        EmpAdress empAdress = AddressFactory.createEmployeeAddress("testApi","admin","1000");
        EmployeeDetails employeeDetails = EmployeeFactory.createEmployee("testApi","test","admin", empAdress);
        restTemplate.postForObject(URI, employeeDetails, EmployeeDetails.class );
    }
    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/emp/{id}";
        RestTemplate restTemplate = new RestTemplate();
        EmployeeDetails employeeDetails = restTemplate.getForObject(URI, EmployeeDetails.class, "7");
        Assert.assertNotNull(employeeDetails);
        Assert.assertEquals("admin", employeeDetails.getEmpName());

        Assert.assertEquals("test", employeeDetails.getEmpLastName());
    }
    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/emp/{id}";
        RestTemplate restTemplate = new RestTemplate();
        EmployeeDetails employeeDetails = restTemplate.getForObject(URI, EmployeeDetails.class, "5");
        if(employeeDetails !=null) {
            String UPDATE_URI = "http://localhost:8080/emp";
            EmployeeDetails updateEmployee = new EmployeeDetails.EmployeeBuilder()
                    .copy(employeeDetails)
                    .name("successApi")
                    .build();
            restTemplate.put(UPDATE_URI,updateEmployee);
            EmployeeDetails updatedEmployee= restTemplate.getForObject(URI, EmployeeDetails.class, "5");

            Assert.assertNotEquals(updatedEmployee.getEmpName(), updatedEmployee.getEmpName());
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/emp";
        RestTemplate restTemplate = new RestTemplate();
        Set employeeSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(employeeSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/emp/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"1");
        EmployeeDetails employeeDetails = restTemplate.getForObject(URI, EmployeeDetails.class, "1");

        Assert.assertNull(employeeDetails);


    }
}
