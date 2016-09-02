package BusTransportingSystem.api;

import BusTransportingSystem.domain.EmployeeDetails;
import BusTransportingSystem.services.impl.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Cornelious on 8/11/2016.
 */
@RestController
@RequestMapping(value="/emp")


class EmployeeController {
    @Autowired
    private EmployeeServices service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public EmployeeDetails findById(@PathVariable Long id){
        return   service.readById(id);
    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EmployeeDetails create(@RequestBody EmployeeDetails resource){
        return  service.create(resource);
    }

    @RequestMapping(method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody EmployeeDetails resource){
        service.update(resource);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<EmployeeDetails> findAll(){
        return service.readAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        EmployeeDetails employeeToDelete= service.readById(id);
        if(employeeToDelete!=null) {
            service.delete(employeeToDelete);
        }
    }
}
