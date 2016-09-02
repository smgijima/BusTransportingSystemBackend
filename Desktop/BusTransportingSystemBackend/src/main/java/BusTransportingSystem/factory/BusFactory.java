package BusTransportingSystem.factory;

import BusTransportingSystem.domain.BusDetails;

/**
 * Created by Cornelious on 7/31/2016.
 */
public class BusFactory {
    public static BusDetails createBus(String numPlate, int seatsNum){
        return new BusDetails.BusBuilder()
                .getnumberPlate(numPlate)
                .seats(seatsNum)
                .build();
    }
}
