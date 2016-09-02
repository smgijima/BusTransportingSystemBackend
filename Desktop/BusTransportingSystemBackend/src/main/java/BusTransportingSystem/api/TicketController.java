package BusTransportingSystem.api;

import BusTransportingSystem.domain.Ticket;
import BusTransportingSystem.services.impl.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Siphiwo on 25/08/2016.
 */
@RestController
@RequestMapping(value="/tick")
 class TicketController {

    @Autowired
    private TicketService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Ticket findById(@PathVariable Long id){
        return   service.readById(id);
    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Ticket create(@RequestBody Ticket resource){
        return  service.create(resource);
    }

    @RequestMapping(method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Ticket resource){
        service.update(resource);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Ticket> findAll(){
        return service.readAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        Ticket passengerToDelete= service.readById(id);
        if(passengerToDelete!=null) {
            service.delete(passengerToDelete);
        }
    }
}
