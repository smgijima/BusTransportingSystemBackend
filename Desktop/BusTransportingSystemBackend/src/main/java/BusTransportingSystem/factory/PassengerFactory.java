package BusTransportingSystem.factory;

import BusTransportingSystem.domain.Traveler;
import BusTransportingSystem.domain.TravelerAddress;

/**
 * Created by Cornelious on 7/31/2016.
 */
public class PassengerFactory {
    public static Traveler createPassenger(String name, String lastName, TravelerAddress address){
        return new Traveler.PassengerBuilder()
                .name(name)
                .lastName(lastName)
                .address(address)
                .build();
    }
}
