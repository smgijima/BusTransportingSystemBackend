package BusTransportingSystem.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siphiwo on 08/28/2016.
 */
@Entity
public class BusDetails implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numberPlate;
    private int numberOfSeats;


    public BusDetails(BusBuilder objBusBuilder) {
        this.id = objBusBuilder.id;
        this.numberPlate = objBusBuilder.numberPlate;
        this.numberOfSeats = objBusBuilder.numberOfseats;

    }
public BusDetails(){}
    public Long getId() {
        return id;
    }



    public String getNumberPlate() {
        return numberPlate;
    }

    public int getSeats() {
        return numberOfSeats;
    }

    public static class BusBuilder {
        private Long id;
        private String numberPlate;
        private int numberOfseats;

        public BusBuilder Id(Long id) {
            this.id = id;
            return this;
        }

        public BusBuilder getnumberPlate(String numberPlate) {
            this.numberPlate = numberPlate;
            return this;
        }

        public BusBuilder seats(int numberOfSeats) {
            this.numberOfseats = numberOfSeats;
            return this;
        }

        public BusBuilder copy(BusDetails objBusDetails) {
            this.id = objBusDetails.id;
            this.numberPlate = objBusDetails.numberPlate;
            this.numberOfseats = objBusDetails.numberOfSeats;
            return this;
        }

        public BusDetails build() {
            return new BusDetails(this);
        }


    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
