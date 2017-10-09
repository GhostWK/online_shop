package ru.ncedu.onlineshop.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ncedu.onlineshop.entities.EntityUser;
import ru.ncedu.onlineshop.repositories.RepositoryUser;
import ru.ncedu.onlineshop.wrappers.WrapperUser;

@Controller
public class ServiceUser {
    @Autowired
    private RepositoryUser repositoryUser;



    public ResponseEntity<EntityUser> registration( WrapperUser user) {

        if (repositoryUser.findByLogin(user.getLogin()).isPresent()) {
            return new ResponseEntity<EntityUser>(HttpStatus.ALREADY_REPORTED);
        }


        EntityUser eUser = new EntityUser();
        eUser.setLogin(user.getLogin());
        eUser.setHashPassword(DigestUtils.sha1Hex(user.getPassword()));
        EntityUser regUser = repositoryUser.save(eUser);

        return new ResponseEntity<EntityUser>(regUser, HttpStatus.CREATED);
    }

    public Iterable<EntityUser> get() {
        return repositoryUser.findAll();
    }

    public ResponseEntity<EntityUser> get(@PathVariable Long id){
        return new ResponseEntity<EntityUser>(repositoryUser.findOne(id), HttpStatus.OK);
    }
}
