package BusTransportingSystem.factory;

import BusTransportingSystem.domain.Ticket;

/**
 * Created by Cornelious on 7/31/2016.
 */
public class TicketFactory {
    public static Ticket createTicket(String type, String route, double cost)
    {
        return new Ticket.TicketBuilder()
                .ticketType(type)
                .route(route)
                .cost(cost)
                .build();

    }
}
