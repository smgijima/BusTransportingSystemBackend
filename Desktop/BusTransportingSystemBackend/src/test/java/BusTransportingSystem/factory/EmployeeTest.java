package BusTransportingSystem.factory;

import BusTransportingSystem.domain.EmpAdress;
import BusTransportingSystem.domain.EmployeeDetails;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cornelious on 8/7/2016.
 */
public class EmployeeTest {
    private EmployeeDetails employeeDetails;
    private EmpAdress employeeAdress;

    @Test
    public void testCreate() throws Exception {
        employeeAdress = AddressFactory.createEmployeeAddress("admin","testCity","1000");
        employeeDetails = EmployeeFactory.createEmployee("test id","admin","testlastname",employeeAdress);
        Assert.assertNotNull(employeeDetails);
        Assert.assertEquals(employeeDetails.getObjAddress().getStreet(),"admin");
    }

    @Test
    public void testUpdate() throws Exception {
        employeeAdress = AddressFactory.createEmployeeAddress("admin","testCity","1000");
        employeeDetails = EmployeeFactory.createEmployee("test id","admin","testlastname",employeeAdress);

        EmployeeDetails copyEmployee = new EmployeeDetails.EmployeeBuilder()
                .copy(employeeDetails)
                .address(employeeAdress)
                .name("testName")
                .build();
        Assert.assertEquals(copyEmployee.getEmpName(),"testName");

    }
}
