package BusTransportingSystem.services.impl;

import BusTransportingSystem.domain.BusDetails;
import BusTransportingSystem.repository.BusRepository;
import BusTransportingSystem.services.IBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 7/31/2016.
 */
@Service
public class BusService implements IBusService {
    @Autowired
   private BusRepository repository;
    @Override
    public BusDetails create(BusDetails entity) {
        return repository.save(entity);
    }

    @Override
    public BusDetails readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<BusDetails> readAll() {
        Iterable<BusDetails> buses = repository.findAll();
        Set busSet = new HashSet();
        for(BusDetails busDetails : buses) {
        busSet.add(busDetails);
        }
        return busSet;
    }

    @Override
    public BusDetails update(BusDetails entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(BusDetails entity) {
        repository.delete(entity);
    }
}
