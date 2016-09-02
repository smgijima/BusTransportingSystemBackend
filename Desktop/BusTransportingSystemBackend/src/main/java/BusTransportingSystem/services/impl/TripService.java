package BusTransportingSystem.services.impl;

import BusTransportingSystem.domain.TripDetails;
import BusTransportingSystem.repository.TripRepository;
import BusTransportingSystem.services.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 7/31/2016.
 */
@Service
public class TripService implements ITripService {
    @Autowired
    private TripRepository repository;

    @Override
    public TripDetails create(TripDetails entity) {
        return repository.save(entity);
    }

    @Override
    public TripDetails readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<TripDetails> readAll() {
        Iterable<TripDetails> trips = repository.findAll();
        Set<TripDetails> tripDetailsSet = new HashSet<>();
        for(TripDetails tripDetails : trips){
            tripDetailsSet.add(tripDetails);
        }
        return tripDetailsSet;
    }

    @Override
    public TripDetails update(TripDetails entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(TripDetails entity) {
        repository.delete(entity);
    }
}
