package BusTransportingSystem.services;
import BusTransportingSystem.App;
import BusTransportingSystem.domain.Ticket;
import BusTransportingSystem.factory.TicketFactory;
import BusTransportingSystem.services.impl.TicketService;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.testng.annotations.Test;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
/**
 * Created by Cornelious on 8/7/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class TicketServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private TicketService service;

    @Test
    public void testCreate() throws Exception {
        //repository.deleteAll();

        Ticket ticket = TicketFactory.createTicket("admin","test",200.0);
        Ticket savedticket=service.create(ticket);

        assertNotNull("CREATE TEST",savedticket);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testReadAll() throws Exception {
        Iterable<Ticket> tickets =  service.readAll();

        assertNotNull("READ TEST",tickets);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdate() throws Exception {
        Ticket ticketFound= service.readById(1L);
        Ticket updateTicket = new Ticket.TicketBuilder()
                .copy(ticketFound)
                .ticketType("service testupdate")
                .build();
        Ticket updatedTicket=service.update(updateTicket);
        Assert.assertSame("UPDATE TEST",updateTicket.getTicketType(),updatedTicket.getTicketType());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Ticket foundTicket = service.readById(3L);
        if(foundTicket !=null) {
            assertNotNull("BEFORE DELETE TEST",foundTicket);
            service.delete(foundTicket);
            Ticket deletedTicket = service.readById(3L);

            assertNull("DELETE TEST",deletedTicket);
        }

    }
}
