package ru.ncedu.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class EntityUser {

    @Id
    @GeneratedValue
    private Long id;
    @Size(max = 20, min = 4)
//    @JsonProperty(value = "name")
    private String login;
    @JsonIgnore
    private String hashPassword;

    @OneToOne
    private EntityCart cart;

    @OneToMany(mappedBy = "user")
    private List<EntityContact> contact;

    public EntityUser() {
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

    public List<EntityContact> getContact() {
        return contact;
    }

    public void setContact(List<EntityContact> contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "EntityUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                '}';
    }
}