package BusTransportingSystem.factory;

import BusTransportingSystem.domain.Ticket;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Cornelious on 8/7/2016.
 */
public class TicketTest {
    private Ticket ticket;

    @Test
    public void testCreate() throws Exception {
        ticket = TicketFactory.createTicket("Adult","CPT-JHB",200.0);

        Assert.assertEquals(ticket.getTicketType(),"Adult");
        Assert.assertEquals(ticket.getRoute(),"CPT-JHB");
        Assert.assertEquals(ticket.getCost(),200.0);
    }

    @Test
    public void testUpdate() throws Exception {
        ticket = TicketFactory.createTicket("Adult","CPT-JHB",200.0);
        Ticket copyTicket = new Ticket.TicketBuilder()
                .copy(ticket)
                .ticketType("Adult")
                .build();
        Assert.assertEquals(copyTicket.getCost(),200.0);
        Assert.assertEquals(copyTicket.getRoute(),"CPT-JHB");
        Assert.assertEquals(copyTicket.getTicketType(),"Adult");

    }
}
