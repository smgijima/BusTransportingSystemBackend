package BusTransportingSystem.domain;

import javax.persistence.*;

import java.io.Serializable;



/**
 * Created by Siphiwo on 08/28/2016.
 */
@Entity
public class Traveler implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Embedded
    private TravelerAddress objAdress;
    private Traveler(PassengerBuilder objBuilder)
    {
        this.id=objBuilder.id;
        this.firstName =objBuilder.name;
        this.lastName=objBuilder.lastName;
        this.objAdress=objBuilder.objAdress;
    }
    private Traveler(){}
    public Long getId(){
    return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public TravelerAddress getObjAdress() {
        return objAdress;
    }

    public static class PassengerBuilder
    {
        private Long id;
        private String name;
        private String lastName;
        private TravelerAddress objAdress;

        public PassengerBuilder id(Long id)
        {
            this.id=id;
            return this;
        }

        public PassengerBuilder name(String name)
        {
            this.name=name;
            return this;
        }
        public PassengerBuilder lastName(String lastName)
        {
            this.lastName=lastName;
            return this;
        }
        public PassengerBuilder address(TravelerAddress objAdress)
        {
            this.objAdress=objAdress;
            return this;
        }
        public PassengerBuilder copy(Traveler objTraveler)
        {
            this.id= objTraveler.id;
            this.name= objTraveler.firstName;
            this.lastName= objTraveler.lastName;
            this.objAdress= objTraveler.objAdress;
            return  this;
        }
        public Traveler build()
        {
            return new Traveler(this);
        }


    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
