package ru.ncedu.onlineshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityCart;
import ru.ncedu.onlineshop.entities.EntityChosenGoods;
import ru.ncedu.onlineshop.entities.EntityUser;
import ru.ncedu.onlineshop.repositories.CartRepository;
import ru.ncedu.onlineshop.repositories.ChosenGoodsRepository;
import ru.ncedu.onlineshop.repositories.GoodsRepository;
import ru.ncedu.onlineshop.repositories.UserRepository;
import ru.ncedu.onlineshop.wrappers.WrapperCart;
import ru.ncedu.onlineshop.wrappers.WrapperChosenGoods;

import java.util.List;

@RestController
@RequestMapping(value = "/onlineShop/carts")
public class CartRestController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ChosenGoodsRepository chosenGoodsRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Iterable<EntityChosenGoods> get(@PathVariable Long id){
        return cartRepository.getOne(id).getChosenGoods();
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<EntityCart> add(@RequestBody WrapperChosenGoods chosenGoods){
        EntityCart cart = cartRepository.findOne(chosenGoods.getCartId());
        EntityChosenGoods entityChosenGoods = new EntityChosenGoods(goodsRepository.findOne(chosenGoods.getGoodsId()), chosenGoods.getAmount());
        entityChosenGoods = chosenGoodsRepository.saveAndFlush(entityChosenGoods);
        cart.add(entityChosenGoods);
        cart = cartRepository.saveAndFlush(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    Iterable<EntityCart> get(){
        return cartRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<EntityUser> createCart(@RequestBody WrapperCart cart){

        Long userId = cart.getUserId();
        EntityUser user = userRepository.findOne(userId);
        EntityCart newCart = cartRepository.save(new EntityCart());
        user.setCart(newCart);
        cartRepository.saveAndFlush(newCart);
        user = userRepository.saveAndFlush(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
