package BusTransportingSystem.api;

import BusTransportingSystem.domain.Login;
import BusTransportingSystem.services.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Siphiwo on 25/08/2016.
 */
@RestController
@RequestMapping(value="/acc")

 class AccountController {

    @Autowired
   private    AccountService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
   public Login findById(@PathVariable Long id){
        return   service.readById(id);
    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Login create(@RequestBody Login resource){
        return  service.create(resource);
    }

    @RequestMapping(method =  RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Login resource){
    service.update(resource);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<Login> findAll(){
        return service.readAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")Long id){
        Login loginToDelete = service.readById(id);
        if(loginToDelete !=null) {
            service.delete(loginToDelete);
        }
    }
}
