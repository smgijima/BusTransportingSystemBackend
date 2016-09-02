package BusTransportingSystem.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siphiwo on 08/28/2016.
 */
@Entity
public class BusDistributor implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String contactNumber;

    public BusDistributor(SupplierBuilder suplierBuilder){
        this.id=suplierBuilder.id;
        this.name=suplierBuilder.name;
        this.contactNumber=suplierBuilder.contactNumber;
    }
    private BusDistributor(){}
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public static class SupplierBuilder{
        private  Long id;
        private String name;
        private String contactNumber;

        public SupplierBuilder id(Long id){
            this.id=id;
            return  this;
        }
        public SupplierBuilder name(String name){
            this.name=name;
            return  this;
        }
        public SupplierBuilder contactNumber(String contactNumber){
            this.contactNumber=contactNumber;
            return this;
        }
        public SupplierBuilder copy(BusDistributor supplier){
            this.id=supplier.id;
            this.name=supplier.name;
            this.contactNumber=supplier.contactNumber;
            return  this;
        }
        public BusDistributor build(){
            return new BusDistributor(this);
        }
    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
