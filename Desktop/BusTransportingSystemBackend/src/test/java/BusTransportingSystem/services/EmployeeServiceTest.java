package BusTransportingSystem.services;

import BusTransportingSystem.App;
import BusTransportingSystem.domain.EmpAdress;
import BusTransportingSystem.domain.EmployeeDetails;
import BusTransportingSystem.factory.AddressFactory;
import BusTransportingSystem.factory.EmployeeFactory;
import BusTransportingSystem.services.impl.EmployeeServices;
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
public class EmployeeServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private EmployeeServices services;

    @Test
    public void testCreateEmployee() throws Exception {
        //repository.deleteAll();
        EmpAdress empAdress = AddressFactory.createEmployeeAddress("admin","test","0000");
        EmployeeDetails employeeDetails = EmployeeFactory.createEmployee(" service test","admin","test", empAdress);
        EmployeeDetails savedEmployee=services.create(employeeDetails);

        assertNotNull("CREATE TEST",savedEmployee);
    }

    @Test(dependsOnMethods = "testCreateEmployee")
    public void testReadAll() throws Exception {
        Iterable<EmployeeDetails> employees =  services.readAll();

        assertNotNull("READ TEST",employees);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdate() throws Exception {
        EmployeeDetails employeeFound= services.readById(1L);
        EmployeeDetails updateEmployee = new EmployeeDetails.EmployeeBuilder()
                .copy(employeeFound)
                .name("service testupdate")
                .build();
        EmployeeDetails updatedEmployee=services.update(updateEmployee);
        Assert.assertSame("UPDATE TEST",updateEmployee.getEmpName(),updatedEmployee.getEmpName());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        EmployeeDetails foundEmployee = services.readById(3L);
        if(foundEmployee !=null) {
            assertNotNull("BEFORE DELETE TEST",foundEmployee);
            services.delete(foundEmployee);
            EmployeeDetails deletedEmployee = services.readById(3L);

            assertNull("DELETE TEST",deletedEmployee);
        }

    }
}
