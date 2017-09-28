package ru.ncedu.onlineshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityGoods;
import ru.ncedu.onlineshop.repositories.GoodsRepository;
import ru.ncedu.onlineshop.wrappers.WrapperGoods;
import ru.ncedu.onlineshop.wrappers.WrapperUser;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/onlineShop/goods")
public class GoodsRestController {


    @Autowired
    private GoodsRepository goodsRepository;



    @RequestMapping(method = RequestMethod.POST)
    EntityGoods add(@RequestBody WrapperGoods wrapperGoods){
        Optional<EntityGoods> temp = goodsRepository.getByName(wrapperGoods.getName());
        if (!temp.isPresent()) {
            return goodsRepository.save(new EntityGoods(wrapperGoods));
        }else {
            //goodsRepository.
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    EntityGoods get(@PathVariable Long id){
        Optional<EntityGoods> opt = goodsRepository.getById(id);

        if(opt.isPresent()){
            return opt.get();
        }else {
            //TODO 404 not found
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    Iterable<EntityGoods> get(){
//        return goodsRepository.findAll();
        return goodsRepository.findAll(new Sort(new Sort.Order("quantity")));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    ResponseEntity delete(@PathVariable Long id){
        EntityGoods goods = get(id);
        if(goods == null){
            //TODO 404 not found
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        goodsRepository.delete(goods);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    EntityGoods update(@PathVariable Long id, @RequestBody WrapperGoods goods){
//        Optional<EntityGoods> opt = goodsRepository.getById(id);
        EntityGoods newGoods = new EntityGoods(goods);
        newGoods.setId(id);

        return goodsRepository.save(newGoods);
    }
}
