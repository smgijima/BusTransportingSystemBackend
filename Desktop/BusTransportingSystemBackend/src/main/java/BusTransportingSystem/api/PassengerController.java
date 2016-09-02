package BusTransportingSystem.api;

import BusTransportingSystem.domain.Traveler;
import BusTransportingSystem.services.impl.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Siphiwo on 25/08/2016.
 */
@RestController
@RequestMapping(value="/pass")
 class PassengerController {
    @Autowired
    private PassengerService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Traveler findById(@PathVariable Long id){
        return   service.readById(id);
    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Traveler create(@RequestBody Traveler resource){
        return  service.create(resource);
    }

    @RequestMapping(method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Traveler resource){
        service.update(resource);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Traveler> findAll(){
        return service.readAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        Traveler travelerToDelete = service.readById(id);
        if(travelerToDelete !=null) {
            service.delete(travelerToDelete);
        }
    }
}
