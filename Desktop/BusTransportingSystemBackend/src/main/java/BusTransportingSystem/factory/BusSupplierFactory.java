package BusTransportingSystem.factory;

import BusTransportingSystem.domain.BusDistributor;

/**
 * Created by Cornelious on 7/31/2016.
 */
public class BusSupplierFactory {
    public static BusDistributor createSupplier(String name, String contactNumber){
        return new BusDistributor.SupplierBuilder()
                .name(name)
                .contactNumber(contactNumber)
                .build();
    }
}
