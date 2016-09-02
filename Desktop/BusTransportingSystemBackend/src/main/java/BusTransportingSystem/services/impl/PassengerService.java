package BusTransportingSystem.services.impl;

import BusTransportingSystem.domain.Traveler;
import BusTransportingSystem.repository.PassengerRepository;
import BusTransportingSystem.services.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 7/31/2016.
 */
@Service
public class PassengerService implements IPassengerService{
    @Autowired
    private PassengerRepository repository;

    @Override
    public Traveler create(Traveler entity) {
        return repository.save(entity);
    }

    @Override
    public Traveler readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<Traveler> readAll() {
        Iterable<Traveler> passengers = repository.findAll();
        Set<Traveler> travelerSet = new HashSet<>();

        for (Traveler traveler : passengers){
            travelerSet.add(traveler);
        }
        return travelerSet;
    }

    @Override
    public Traveler update(Traveler entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Traveler entity) {
        repository.delete(entity);
    }
}
