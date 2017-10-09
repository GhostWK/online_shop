package ru.ncedu.onlineshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityGoods;
import ru.ncedu.onlineshop.entities.EntityKeyWord;
import ru.ncedu.onlineshop.services.ServiceKeyWord;
import ru.ncedu.onlineshop.wrappers.WrapperKeyWord;

import java.util.*;

@RestController
@RequestMapping(value = "/onlineShop/keyWords")
public class RestControllerKeyWord {


    @Autowired
    private ServiceKeyWord serviceKeyWord;

    @RequestMapping(method = RequestMethod.GET, value = "/{k}:{v}")
    List<EntityGoods> get(@PathVariable String k, @PathVariable String v){
        return serviceKeyWord.get(k, v);
    }

    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity add(@RequestBody WrapperKeyWord word){
        return serviceKeyWord.add(word);
    }

    @RequestMapping(method = RequestMethod.GET)
    Iterable<EntityKeyWord> get(){
        return serviceKeyWord.get();
    }


}
