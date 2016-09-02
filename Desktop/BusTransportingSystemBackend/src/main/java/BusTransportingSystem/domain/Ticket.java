package BusTransportingSystem.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siphiwo on 25/08/2016.
 */
@Entity
public class Ticket implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String ticketType;
    private String route;
    private double cost;
    private Ticket(TicketBuilder objBuilder)
    {
        this.id =objBuilder.id;
        this.ticketType=objBuilder.ticketType;
        this.route=objBuilder.route;
        this.cost=objBuilder.cost;
    }
private Ticket(){}
    public Long getId() {
        return id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getRoute() {
        return route;
    }

    public double getCost() {
        return cost;
    }
    public static class TicketBuilder
    {
        private Long id;
        private  String ticketType;
        private String route;
        private double cost;
        public TicketBuilder ticketId(Long id)
        {
            this.id =id;
            return this;
        }
        public TicketBuilder ticketType(String ticketType)
        {
            this.ticketType=ticketType;
            return this;
        }
        public TicketBuilder route(String route)
        {
            this.route=route;
            return this;
        }
        public TicketBuilder cost(double cost)
        {
            this.cost=cost;
            return this;
        }
        public  TicketBuilder copy(Ticket objTicket)
        {
            this.id =objTicket.id;
            this.ticketType=objTicket.ticketType;
            this.route=objTicket.route;
            this.cost=objTicket.cost;
            return this;
        }
        public Ticket build()
        {
            return new Ticket(this);
        }
    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
