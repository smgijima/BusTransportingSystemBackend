package BusTransportingSystem.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siphiwo on 08/28/2016.
 */
@Entity
public class TripDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String departure;
    private String time;
    private String destination;


    private TripDetails(TripBuilder objBuilder) {
        this.id = objBuilder.id;
        this.departure = objBuilder.departure;
        this.time = objBuilder.time;
        this.destination = objBuilder.destination;
    }
    private TripDetails(){}
    public Long getId() {
        return id;
    }

    public String getDeparture() {
        return departure;
    }

    public String getTime() {
        return time;
    }

    public String getDestination() {
        return destination;
    }


    public static class TripBuilder {
        private String departure;
        private String time;
        private String destination;
        private Long id;


        public TripBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TripBuilder departure(String departure) {
            this.departure = departure;
            return this;
        }

        public TripBuilder time(String time) {
            this.time = time;
            return this;
        }

        public TripBuilder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public TripBuilder copy(TripDetails objTripDetails) {
            this.id = objTripDetails.id;
            this.departure = objTripDetails.departure;
            this.time = objTripDetails.time;
            this.destination = objTripDetails.destination;
            return this;
        }

        public TripDetails build() {
            return new TripDetails(this);
        }


    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}