package BusTransportingSystem.factory;


import BusTransportingSystem.domain.EmpAdress;
import BusTransportingSystem.domain.EmployeeDetails;

/**
 * Created by Cornelious on 7/31/2016.
 */
public class EmployeeFactory {
    public static EmployeeDetails createEmployee(String idNum, String name, String lastname, EmpAdress address){
        return new EmployeeDetails.EmployeeBuilder()
                .idNumber(idNum)
                .name(name)
                .lastName(lastname)
                .address(address)
                .build();
    }
}
