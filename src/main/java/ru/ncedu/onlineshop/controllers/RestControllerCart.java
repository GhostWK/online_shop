package ru.ncedu.onlineshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityCart;
import ru.ncedu.onlineshop.entities.EntityChosenGoods;
import ru.ncedu.onlineshop.entities.EntityUser;
import ru.ncedu.onlineshop.services.ServiceCart;
import ru.ncedu.onlineshop.wrappers.WrapperCart;
import ru.ncedu.onlineshop.wrappers.WrapperChosenGoods;

@RestController
@RequestMapping(value = "/onlineShop/carts")
public class RestControllerCart {


    @Autowired
    private ServiceCart serviceCart;


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Iterable<EntityChosenGoods> get(@PathVariable Long id){
        return serviceCart.get(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<EntityCart> add(@RequestBody WrapperChosenGoods chosenGoods){
        return serviceCart.add(chosenGoods);
    }

    @RequestMapping(method = RequestMethod.GET)
    Iterable<EntityCart> get(){
        return serviceCart.get();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<EntityUser> createCart(@RequestBody WrapperCart cart){
        return serviceCart.createCart(cart);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    ResponseEntity<EntityCart> pushCart(@PathVariable Long id){
        return serviceCart.push(id);
    }

}
