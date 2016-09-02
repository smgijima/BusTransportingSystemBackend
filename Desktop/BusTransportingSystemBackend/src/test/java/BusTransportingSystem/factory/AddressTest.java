package BusTransportingSystem.factory;

import BusTransportingSystem.domain.EmpAdress;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Siphiwo on 08/28/2016.
 */
public class AddressTest {
    @Test
    public void testCreate(){
        EmpAdress address = AddressFactory.createEmployeeAddress(
                "199 solaria","Muizenberg","7945"
        );
        Assert.assertEquals(address.getStreet(),"199 solaria");
        Assert.assertEquals(address.getCity(),"Muizenberg");
        Assert.assertEquals(address.getCode(),"7945");


    }

    @Test
    public void testUpdate(){
        EmpAdress address = AddressFactory.createEmployeeAddress(
                "199 solaria","Muizenberg","7945"
        );
        EmpAdress copyAddress = new EmpAdress.AddressBuilder()
                .copy(address)
                .code("8000")
                .build();


        Assert.assertEquals(copyAddress.getStreet(),"199 solaria");
        Assert.assertEquals(copyAddress.getCity(),"Muizenberg");
        Assert.assertEquals(copyAddress.getCode(),"8000");

    }
}
