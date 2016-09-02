package BusTransportingSystem.repository;

import BusTransportingSystem.App;
import BusTransportingSystem.domain.EmpAdress;
import BusTransportingSystem.domain.EmployeeDetails;
import BusTransportingSystem.factory.AddressFactory;
import BusTransportingSystem.factory.EmployeeFactory;
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
public class EmployeeCrudTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private   EmployeeRepository repository;

    @org.testng.annotations.Test
    public void testCreateEmployee() throws Exception {
        //repository.deleteAll();
        EmpAdress empAdress = AddressFactory.createEmployeeAddress("admin","test","0000");
        EmployeeDetails employeeDetails = EmployeeFactory.createEmployee("2016test","admin","test", empAdress);
        EmployeeDetails savedEmployee=repository.save(employeeDetails);

        assertNotNull("CREATE TEST",savedEmployee);
    }

    @Test(dependsOnMethods = "testCreateEmployee")
    public void testReadAll() throws Exception {
        Iterable<EmployeeDetails> employees =  repository.findAll();

        assertNotNull("READ TEST",employees);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdate() throws Exception {
        EmployeeDetails employeeFound= repository.findOne(1L);
        EmployeeDetails updateEmployee = new EmployeeDetails.EmployeeBuilder()
                .copy(employeeFound)
                .name("testupdate")
                .build();
        EmployeeDetails updatedEmployee=repository.save(updateEmployee);
        Assert.assertSame("UPDATE TEST",updateEmployee.getEmpName(),updatedEmployee.getEmpName());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        EmployeeDetails foundEmployee = repository.findOne(2L);
        if(foundEmployee !=null) {
            assertNotNull("BEFORE DELETE TEST",foundEmployee);
            repository.delete(2L);
            EmployeeDetails deletedEmployee = repository.findOne(2L);

            assertNull("DELETE TEST",deletedEmployee);
        }

    }
}
