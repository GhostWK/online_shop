package ru.ncedu.onlineshop.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.onlineshop.entities.EntityUser;
import ru.ncedu.onlineshop.wrappers.WrapperUser;
import ru.ncedu.onlineshop.repositories.UserRepository;

import java.util.Optional;


@RestController
@RequestMapping("/onlineShop/users")
public class UserRestController {
    @Autowired
    private  UserRepository userRepository;


//
//    @RequestMapping(method = RequestMethod.POST)
//    EntityUser get(@RequestBody WrapperUser user){
//        Optional<EntityUser> opt = userRepository.findByLogin(user.getLogin());
//        if(!opt.isPresent() || !opt.get().getHashPassword().equals(DigestUtils.sha1Hex(user.getPassword()))){
//            //TODO: user not found
//            return null;
//        }
//        return opt.get();
//    }

    @RequestMapping(method = RequestMethod.POST )
    ResponseEntity<EntityUser> registration(@RequestBody WrapperUser user){
        if(userRepository.findByLogin(user.getLogin())==null){
            //TODO: same login is already exists
            return null;
        }

        EntityUser eUser = new EntityUser();
        eUser.setLogin(user.getLogin());
        eUser.setHashPassword(DigestUtils.sha1Hex(user.getPassword()));
        EntityUser regUser = userRepository.save(eUser);

        return new ResponseEntity<EntityUser>(regUser, HttpStatus.CREATED);
    }





}
