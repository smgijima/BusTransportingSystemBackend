package BusTransportingSystem.services.impl;

import BusTransportingSystem.domain.BusDistributor;
import BusTransportingSystem.repository.BusSupplierRepository;
import BusTransportingSystem.services.IBusSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 7/31/2016.
 */
@Service
public class BusSupplierService implements IBusSupplierService {
    @Autowired
   private BusSupplierRepository repository;
    @Override
    public BusDistributor create(BusDistributor entity) {
        return repository.save(entity) ;
    }

    @Override
    public BusDistributor readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<BusDistributor> readAll() {
        Iterable<BusDistributor> suppliers =repository.findAll();
        Set supplierSet = new HashSet();
        for(BusDistributor supplier:suppliers){
            supplierSet.add(supplier);
        }
        return supplierSet;
    }

    @Override
    public BusDistributor update(BusDistributor entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(BusDistributor entity) {
        repository.delete(entity);
    }
}
