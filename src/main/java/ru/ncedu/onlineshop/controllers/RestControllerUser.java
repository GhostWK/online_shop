package ru.ncedu.onlineshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityUser;
import ru.ncedu.onlineshop.services.ServiceUser;
import ru.ncedu.onlineshop.wrappers.WrapperUser;


@RestController
@RequestMapping("/onlineShop/users")
public class RestControllerUser {
    @Autowired
    private ServiceUser serviceUser;



    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<EntityUser> registration(@RequestBody WrapperUser user) {
        return serviceUser.registration(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    Iterable<EntityUser> get() {
        return serviceUser.get();
    }

    @RequestMapping(method = RequestMethod.GET, value ="/{id}")
    ResponseEntity<EntityUser> get(@PathVariable Long id){
        return serviceUser.get(id);
    }


}
