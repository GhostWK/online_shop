package ru.ncedu.onlineshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityGoods;
import ru.ncedu.onlineshop.entities.EntityKeyWord;
import ru.ncedu.onlineshop.repositories.GoodsRepository;
import ru.ncedu.onlineshop.repositories.KeyWordRepository;
import ru.ncedu.onlineshop.wrappers.WrapperKeyWord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/onlineShop/keyWords")
public class KeyWordRestController {


    @Autowired
    private KeyWordRepository keyWordRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{k}:{v}")
    List<EntityGoods> get(@PathVariable String k, @PathVariable String v){
        Optional<EntityKeyWord> keyWord = keyWordRepository.getByKAndV(k, v);
        if(keyWord.isPresent()){
            return new ArrayList(keyWord.get().getGoods());
        }else {
            //TODO 404
        }
        return null;
    }

//    @RequestMapping(method = RequestMethod.POST )
//    EntityKeyWord add(@RequestBody WrapperKeyWord word){
//        Optional<EntityKeyWord> keyWord = keyWordRepository.getByKAndV(word.getK(),word.getV());
//        List<EntityGoods> goods = goodsRepository.getAllById(word.getId());
//        EntityGoods g = goods.get(0);
//        if(goods.size() == 0){
//            //TODO 404 goods
//            return null;
//        }else {
//            //TODO
//        }
//
//        if(keyWord.isPresent()){
//            EntityKeyWord current = keyWord.get();
//
//        }else {
//            EntityKeyWord n = new EntityKeyWord(word.getK(), word.getV());
//        }
//
//
//        return null;
//    }


}
