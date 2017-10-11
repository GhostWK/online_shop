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

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        EntityUser user = repositoryUser.findOne(cart.getUserId());
        EntityCart newCart = repositoryCart.save(new EntityCart());

        user.setCart(newCart);
        repositoryCart.saveAndFlush(newCart);
        user = repositoryUser.saveAndFlush(user);
        newCart.setUser(user);
        repositoryCart.saveAndFlush(newCart);


        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<EntityCart> push(Long cartId){
        EntityCart entityCart = repositoryCart.findOne(cartId);
        if(entityCart == null){
            return new ResponseEntity<EntityCart>(HttpStatus.NOT_FOUND);
        }
        if(!checkPresenceAllGoods(entityCart)){
            //There no enough goods on store
            return new ResponseEntity<EntityCart>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<EntityCart>(issueCart(entityCart), HttpStatus.OK);
    }

    private boolean checkPresenceAllGoods(EntityCart cart){
        Set<EntityChosenGoods> chosenGoodsSet = cart.getChosenGoods();
        for(EntityChosenGoods x : chosenGoodsSet){
            if(x.getAmount() > x.getGoods().getQuantity()) return false;
        }
        return true;
    }

    private EntityCart issueCart(EntityCart cart){
        Set<EntityChosenGoods> chosenGoodsSet = cart.getChosenGoods();
        for(EntityChosenGoods x : chosenGoodsSet){
            Integer quantity = x.getGoods().getQuantity() - x.getAmount();
            x.getGoods().setQuantity(quantity);
            repositoryGoods.save(x.getGoods());
        }

        EntityUser user = cart.getUser();
        cart.setStatus("ISSUED:"+user.getLogin());
        user.setCart(null);

        repositoryUser.saveAndFlush(user);
        repositoryCart.saveAndFlush(cart);
        return cart;
    }


}
