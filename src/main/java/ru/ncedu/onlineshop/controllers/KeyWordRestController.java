package ru.ncedu.onlineshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityGoods;
import ru.ncedu.onlineshop.entities.EntityKeyWord;
import ru.ncedu.onlineshop.repositories.GoodsRepository;
import ru.ncedu.onlineshop.repositories.KeyWordRepository;
import ru.ncedu.onlineshop.wrappers.WrapperGoods;
import ru.ncedu.onlineshop.wrappers.WrapperKeyWord;

import java.util.*;

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

    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity add(@RequestBody WrapperKeyWord word){
        Optional<EntityKeyWord> opt = keyWordRepository.getByKAndV(word.getK(),word.getV());
        EntityGoods goods = goodsRepository.findOne(word.getId());
        if(goods == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        EntityKeyWord keyWord = null;
        if(!opt.isPresent()){
            keyWord = keyWordRepository.save(new EntityKeyWord(word.getK(),word.getV()));
        }else {
            keyWord = opt.get();
        }

        goods = goodsRepository.save(goods.add(keyWord));
        keyWordRepository.save(keyWord.add(goods));


        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    Iterable<WrapperKeyWord> get(){
        List<EntityKeyWord> allKeyWords = keyWordRepository.findAll();
        List<WrapperKeyWord> result = new ArrayList<>(allKeyWords.size());
        for(EntityKeyWord x : allKeyWords){
            WrapperKeyWord added = new WrapperKeyWord(x.getId(), x.getK(), x.getV());
            added.setSet(new HashSet<>());
            for (EntityGoods xx : x.getGoods()){
                added.getSet().add(new WrapperGoods(xx.getName(), xx.getQuantity(), xx.getPrice()));
            }
            result.add(added);
        }
        return result;
    }


}
