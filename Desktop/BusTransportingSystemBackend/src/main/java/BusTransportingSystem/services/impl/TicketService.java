package BusTransportingSystem.services.impl;

import BusTransportingSystem.domain.Ticket;
import BusTransportingSystem.repository.TicketRepository;
import BusTransportingSystem.services.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 7/31/2016.
 */
@Service
public class TicketService implements ITicketService {
    @Autowired
    private   TicketRepository repository;
    @Override
    public Ticket create(Ticket entity) {
        return repository.save(entity);
    }

    @Override
    public Ticket readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Ticket> readAll() {
        Iterable <Ticket> tickets = repository.findAll();
        Set<Ticket> ticketSet = new HashSet<>();

        for(Ticket ticket : tickets){
            ticketSet.add(ticket);
        }
        return ticketSet;
    }

    @Override
    public Ticket update(Ticket entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Ticket entity) {
        repository.delete(entity);
    }
}
