package ru.ncedu.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.codec.digest.DigestUtils;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class EntityUnconfirmedUser {
    @Id
    @GeneratedValue
    private Long id;

    private String login;
    @JsonIgnore
    private String hashPassword;

    private String hashKey;

    public EntityUnconfirmedUser() {
    }

    public EntityUnconfirmedUser(String login, String hashPassword) {
        this.login = login;
        this.hashPassword = DigestUtils.sha1Hex(hashPassword);
        this.hashKey = DigestUtils.sha1Hex(login.split("@")[0] + new Random().nextInt(100));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
