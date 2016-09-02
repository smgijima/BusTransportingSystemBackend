package BusTransportingSystem.factory;

import BusTransportingSystem.domain.TripDetails;

/**
 * Created by Cornelious on 7/31/2016.
 */
public class TripFactory {
    public static TripDetails createTrip(String departure, String time, String destination) {
        return new TripDetails.TripBuilder()
                .departure(departure)
                .time(time)
                .destination(destination)
                .build();
    }
}
