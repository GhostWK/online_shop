package ru.ncedu.onlineshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityGoods;
import ru.ncedu.onlineshop.services.ServiceGoods;
import ru.ncedu.onlineshop.wrappers.WrapperGoods;

@RestController
@RequestMapping(value = "/onlineShop/goods")
public class RestControllerGoods {


    @Autowired
    private ServiceGoods serviceGoods;



    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<EntityGoods> add(@RequestBody WrapperGoods wrapperGoods){
        return serviceGoods.add(wrapperGoods);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseEntity<EntityGoods> get(@PathVariable Long id){
        return serviceGoods.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    Iterable<EntityGoods> get(){
        return serviceGoods.get();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    ResponseEntity delete(@PathVariable Long id){
        return serviceGoods.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    ResponseEntity<EntityGoods> update(@PathVariable Long id, @RequestBody WrapperGoods goods){
        return serviceGoods.update(id, goods);
    }
}
