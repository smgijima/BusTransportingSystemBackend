package BusTransportingSystem.api;

import BusTransportingSystem.domain.Ticket;
import BusTransportingSystem.factory.TicketFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * Created by Siphiwo on 08/28/2016.
 */
public class TicketControllerTest {
    @Test
    public void testCreate(){
        String URI =  "http://localhost:8080/tick";
        RestTemplate restTemplate = new RestTemplate();

        Ticket ticket = TicketFactory.createTicket("testApi","test",200.0);
        restTemplate.postForObject(URI,ticket, Ticket.class );
    }
    @Test
    public void testFindById() {
        String URI =  "http://localhost:8080/tick/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Ticket ticket= restTemplate.getForObject(URI, Ticket.class, "4");
        Assert.assertNotNull(ticket);
        Assert.assertEquals("admin", ticket.getTicketType());

        Assert.assertEquals("test", ticket.getRoute());
    }
    @Test
    public void testUpdate(){
        String URI =  "http://localhost:8080/tick/{id}";
        RestTemplate restTemplate = new RestTemplate();
        Ticket ticket= restTemplate.getForObject(URI, Ticket.class, "1");
        if(ticket!=null) {
            String UPDATE_URI = "http://localhost:8080/tick";
            Ticket updateTicket = new Ticket.TicketBuilder()
                    .copy(ticket)
                    .ticketType("successApi")
                    .build();
            restTemplate.put(UPDATE_URI,updateTicket);
            Ticket updatedTicket= restTemplate.getForObject(URI, Ticket.class, "1");

            Assert.assertEquals(updatedTicket.getTicketType(), updateTicket.getTicketType());
        }
    }
    @Test
    public void testFindAll(){
        String URI =  "http://localhost:8080/tick";
        RestTemplate restTemplate = new RestTemplate();
        Set ticketSet = restTemplate.getForObject(URI,Set.class);
        Assert.assertTrue(ticketSet.size()>0);
    }
    @Test
    public void testDelete(){
        String URI =  "http://localhost:8080/tick/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI,"5");
        Ticket ticket= restTemplate.getForObject(URI, Ticket.class, "5");

        Assert.assertNull(ticket);


    }
}
