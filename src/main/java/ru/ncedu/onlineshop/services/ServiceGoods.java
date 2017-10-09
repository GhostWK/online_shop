package ru.ncedu.onlineshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ncedu.onlineshop.entities.EntityGoods;
import ru.ncedu.onlineshop.repositories.RepositoryGoods;
import ru.ncedu.onlineshop.wrappers.WrapperGoods;

import java.util.Optional;

@Component
public class ServiceGoods {

    @Autowired
    private RepositoryGoods repositoryGoods;


    public ResponseEntity<EntityGoods> add( WrapperGoods wrapperGoods){
        Optional<EntityGoods> temp = repositoryGoods.getByName(wrapperGoods.getName());
        if (!temp.isPresent()) {
            return new ResponseEntity<EntityGoods>(repositoryGoods.save(new EntityGoods(wrapperGoods)), HttpStatus.OK);
        }else {
            return new ResponseEntity<EntityGoods>(temp.get(), HttpStatus.OK);
        }
    }


    public ResponseEntity<EntityGoods> get( Long id){
        Optional<EntityGoods> opt = repositoryGoods.getById(id);

        if(opt.isPresent()){
            return new ResponseEntity<EntityGoods>(opt.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<EntityGoods>(HttpStatus.NOT_FOUND);
        }
    }


    public Iterable<EntityGoods> get(){
        return repositoryGoods.findAll(new Sort(new Sort.Order("quantity")));
    }


    public ResponseEntity delete( Long id){
        EntityGoods goods = get(id).getBody();
        if(goods == null){

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        repositoryGoods.delete(goods);
        return new ResponseEntity(HttpStatus.OK);

    }


    public ResponseEntity<EntityGoods> update(Long id, WrapperGoods goods){
        EntityGoods newGoods = new EntityGoods(goods);
        newGoods.setId(id);

        return new ResponseEntity<EntityGoods>(repositoryGoods.save(newGoods), HttpStatus.OK);
    }
}
