package BusTransportingSystem.services.impl;


import BusTransportingSystem.domain.EmployeeDetails;
import BusTransportingSystem.repository.EmployeeRepository;
import BusTransportingSystem.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cornelious on 7/31/2016.
 */
@Service
public class EmployeeServices implements IEmployeeService {

    @Autowired
    private EmployeeRepository repository;
    @Override
    public EmployeeDetails create(EmployeeDetails entity) {
        return repository.save(entity);
    }

    @Override
    public EmployeeDetails readById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Set<EmployeeDetails> readAll() {
        Iterable<EmployeeDetails> employeeEntities = repository.findAll();
        Set<EmployeeDetails> employeeDetailsSet = new HashSet<>();
        for(EmployeeDetails employeeDetails : employeeEntities){
            employeeDetailsSet.add(employeeDetails);
        }
        return employeeDetailsSet;
    }

    @Override
    public EmployeeDetails update(EmployeeDetails entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(EmployeeDetails entity) {
        repository.delete(entity);
    }
}
