package ru.ncedu.onlineshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ncedu.onlineshop.entities.EntityGoods;
import ru.ncedu.onlineshop.entities.EntityKeyWord;
import ru.ncedu.onlineshop.repositories.RepositoryGoods;
import ru.ncedu.onlineshop.repositories.RepositoryKeyWord;
import ru.ncedu.onlineshop.wrappers.WrapperKeyWord;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class ServiceKeyWord {

    @Autowired
    private RepositoryKeyWord repositoryKeyWord;
    @Autowired
    private RepositoryGoods repositoryGoods;

    public List<EntityGoods> get( String k, String v){
        Optional<EntityKeyWord> keyWord = repositoryKeyWord.getByKAndV(k, v);
        if(keyWord.isPresent()){
            return new ArrayList(keyWord.get().getGoods());
        }else {
            //TODO 404
        }
        return null;
    }

    public ResponseEntity add( WrapperKeyWord word){
        Optional<EntityKeyWord> opt = repositoryKeyWord.getByKAndV(word.getK(),word.getV());
        EntityGoods goods = repositoryGoods.findOne(word.getId());
        if(goods == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        EntityKeyWord keyWord = null;
        if(!opt.isPresent()){
            keyWord = repositoryKeyWord.save(new EntityKeyWord(word.getK(),word.getV()));
        }else {
            keyWord = opt.get();
        }

        goods = repositoryGoods.save(goods.add(keyWord));
        repositoryKeyWord.save(keyWord.add(goods));


        return new ResponseEntity(HttpStatus.OK);
    }

    public Iterable<EntityKeyWord> get(){
//        List<EntityKeyWord> allKeyWords = keyWordRepository.findAll();
//        List<WrapperKeyWord> result = new ArrayList<>(allKeyWords.size());
//        for(EntityKeyWord x : allKeyWords){
//            WrapperKeyWord added = new WrapperKeyWord(x.getId(), x.getK(), x.getV());
//            added.setSet(new HashSet<>());
//            for (EntityGoods xx : x.getGoods()){
//                added.getSet().add(new WrapperGoods(xx.getName(), xx.getQuantity(), xx.getPrice()));
//            }
//            result.add(added);
//        }
//        return result;
        return repositoryKeyWord.findAll();
    }

}
