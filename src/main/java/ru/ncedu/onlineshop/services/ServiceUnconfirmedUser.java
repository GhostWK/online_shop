package ru.ncedu.onlineshop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.ncedu.onlineshop.email.EmailSender;
import ru.ncedu.onlineshop.email.EmailValidator;
import ru.ncedu.onlineshop.email.SendEmail;
import ru.ncedu.onlineshop.entities.EntityUnconfirmedUser;
import ru.ncedu.onlineshop.entities.EntityUser;
import ru.ncedu.onlineshop.repositories.RepositoryUnconfirmedUser;
import ru.ncedu.onlineshop.repositories.RepositoryUser;
import ru.ncedu.onlineshop.wrappers.WrapperUser;

import java.util.Optional;

@Component
public class ServiceUnconfirmedUser {

    private static final String HOST = "localhost:8080/onlineShop/unconfirmed-users/";
    private static final String THEME = "Confirmation of registration in the service";
    private static final String MESSAGE = "To confirm your login, please follow this link:\n";

    @Autowired
    private RepositoryUnconfirmedUser repositoryUnconfirmedUser;
    @Autowired
    private RepositoryUser repositoryUser;

    public ResponseEntity<Iterable<EntityUnconfirmedUser>> get(){
        return new ResponseEntity<>(repositoryUnconfirmedUser.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<EntityUnconfirmedUser> registration(WrapperUser wrapperUser){

        if( isLoginPresent(wrapperUser.getLogin()) || !EmailValidator.isValidEmailAddress(wrapperUser.getLogin())){
            return new ResponseEntity<EntityUnconfirmedUser>(HttpStatus.BAD_REQUEST);
        }

        EntityUnconfirmedUser user = new EntityUnconfirmedUser(wrapperUser.getLogin(), wrapperUser.getPassword());
        user = repositoryUnconfirmedUser.save(user);


        sendEmail(user);

        return new ResponseEntity<EntityUnconfirmedUser>(user, HttpStatus.OK);
    }

    private boolean isLoginPresent(String login){
        return repositoryUnconfirmedUser.getByLogin(login).isPresent() || repositoryUser.findByLogin(login).isPresent();
    }

    private void sendEmail(EntityUnconfirmedUser user){
        EmailSender sender = new EmailSender(user.getLogin(), THEME, MESSAGE + "http://"+HOST +  user.getHashKey());
        sender.start();
    }

    public ResponseEntity<EntityUser> confirming(String hash){
        Optional<EntityUnconfirmedUser> optUnconfirmedUser = repositoryUnconfirmedUser.getByHashKey(hash);
        if(!optUnconfirmedUser.isPresent()){
            return new ResponseEntity<EntityUser>(HttpStatus.NOT_FOUND);
        }

        EntityUnconfirmedUser unconfirmedUser = optUnconfirmedUser.get();
        EntityUser user = new EntityUser(unconfirmedUser.getLogin(), unconfirmedUser.getHashPassword());

        repositoryUser.save(user);
        repositoryUnconfirmedUser.delete(unconfirmedUser);

        return new ResponseEntity<EntityUser>(user, HttpStatus.OK);
    }

}
