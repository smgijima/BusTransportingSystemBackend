package BusTransportingSystem.repository;

import BusTransportingSystem.domain.Ticket;
import BusTransportingSystem.factory.TicketFactory;
import BusTransportingSystem.App;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Siphiwo on 08/28/2016.
 */
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class TicketCrudeTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private TicketRepository repository;

    @org.testng.annotations.Test
    public void testCreate() throws Exception {
        //repository.deleteAll();

        Ticket ticket = TicketFactory.createTicket("admin","test",200.0);
        Ticket savedticket=repository.save(ticket);

        assertNotNull("CREATE TEST",savedticket);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testReadAll() throws Exception {
        Iterable<Ticket> tickets =  repository.findAll();

        assertNotNull("READ TEST",tickets);
    }

    @Test(dependsOnMethods = "testReadAll")
    public void testUpdate() throws Exception {
        Ticket ticketFound= repository.findOne(1L);
        Ticket updateTicket = new Ticket.TicketBuilder()
                .copy(ticketFound)
                .ticketType("testupdate")
                .build();
        Ticket updatedTicket=repository.save(updateTicket);
        Assert.assertSame("UPDATE TEST",updateTicket.getTicketType(),updatedTicket.getTicketType());
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        Ticket foundTicket = repository.findOne(2L);
        if(foundTicket !=null) {
            assertNotNull("BEFORE DELETE TEST",foundTicket);
            repository.delete(2L);
            Ticket deletedTicket = repository.findOne(2L);

            assertNull("DELETE TEST",deletedTicket);
        }

    }
}
