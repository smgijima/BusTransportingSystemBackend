package BusTransportingSystem.factory;

import BusTransportingSystem.domain.EmpAdress;
import BusTransportingSystem.domain.TravelerAddress;

/**
 * Created by Cornelious on 7/31/2016.
 */
public class AddressFactory {
    public static EmpAdress createEmployeeAddress(String street, String city, String code) {
    return new EmpAdress.AddressBuilder()
            .street(street)
            .city(city)
            .code(code)
            .build();
       }
    public static TravelerAddress createPassangerAddress (String street, String city, String code){
        return new TravelerAddress.AddressBuilder()
                .street(street)
                .city(city)
                .code(code)
                .build();
    }


}
