package ru.ncedu.onlineshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityUnconfirmedUser;
import ru.ncedu.onlineshop.entities.EntityUser;
import ru.ncedu.onlineshop.services.ServiceUnconfirmedUser;
import ru.ncedu.onlineshop.wrappers.WrapperUser;


@RestController
@RequestMapping("/onlineShop/unconfirmed-users")
public class RestControllerUnconfirmedUser {

    @Autowired
    private ServiceUnconfirmedUser service;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Iterable<EntityUnconfirmedUser>>get(){
        return service.get();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<EntityUnconfirmedUser> registration(@RequestBody WrapperUser wrapperUser){
        return service.registration(wrapperUser);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{hash}")
    ResponseEntity<EntityUser> confirming(@PathVariable String hash){
        return service.confirming(hash);
    }


}
