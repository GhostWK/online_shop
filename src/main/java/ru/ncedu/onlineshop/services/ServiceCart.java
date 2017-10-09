package ru.ncedu.onlineshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ncedu.onlineshop.entities.EntityCart;
import ru.ncedu.onlineshop.entities.EntityChosenGoods;
import ru.ncedu.onlineshop.entities.EntityUser;
import ru.ncedu.onlineshop.repositories.RepositoryCart;
import ru.ncedu.onlineshop.repositories.RepositoryChosenGoods;
import ru.ncedu.onlineshop.repositories.RepositoryGoods;
import ru.ncedu.onlineshop.repositories.RepositoryUser;
import ru.ncedu.onlineshop.wrappers.WrapperCart;
import ru.ncedu.onlineshop.wrappers.WrapperChosenGoods;

@Component
public class ServiceCart {

    @Autowired
    private RepositoryCart repositoryCart;

    @Autowired
    private RepositoryChosenGoods repositoryChosenGoods;

    @Autowired
    private RepositoryGoods repositoryGoods;

    @Autowired
    private RepositoryUser repositoryUser;

    public Iterable<EntityChosenGoods> get(Long id){
        return repositoryCart.getOne(id).getChosenGoods();
    }

    public ResponseEntity<EntityCart> add(WrapperChosenGoods chosenGoods){
        EntityCart cart = repositoryCart.findOne(chosenGoods.getCartId());
        EntityChosenGoods entityChosenGoods = new EntityChosenGoods(repositoryGoods.findOne(chosenGoods.getGoodsId()), chosenGoods.getAmount());
        entityChosenGoods = repositoryChosenGoods.saveAndFlush(entityChosenGoods);
        cart.add(entityChosenGoods);
        cart = repositoryCart.saveAndFlush(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    public Iterable<EntityCart> get(){
        return repositoryCart.findAll();
    }

    public ResponseEntity<EntityUser> createCart( WrapperCart cart){

        Long userId = cart.getUserId();
        EntityUser user = repositoryUser.findOne(userId);
        EntityCart newCart = repositoryCart.save(new EntityCart());
        user.setCart(newCart);
        repositoryCart.saveAndFlush(newCart);
        user = repositoryUser.saveAndFlush(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
