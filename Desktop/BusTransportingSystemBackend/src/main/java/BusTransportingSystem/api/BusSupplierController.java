package BusTransportingSystem.api;

import BusTransportingSystem.domain.BusDistributor;
import BusTransportingSystem.services.impl.BusSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Siphiwo on 25/08/2016.
 */
@RestController
@RequestMapping(value="/bussup")

 class BusSupplierController {
    @Autowired
    private BusSupplierService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public BusDistributor findById(@PathVariable Long id){
        return   service.readById(id);
    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BusDistributor create(@RequestBody BusDistributor resource){
        return  service.create(resource);
    }

    @RequestMapping(method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody BusDistributor resource){
        service.update(resource);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<BusDistributor> findAll(){
        return service.readAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        BusDistributor supplierToDelete= service.readById(id);
        if(supplierToDelete!=null) {
            service.delete(supplierToDelete);
        }
    }
}
