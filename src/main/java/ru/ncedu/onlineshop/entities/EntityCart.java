package ru.ncedu.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.ncedu.onlineshop.serializers.SerializerUser;
import ru.ncedu.onlineshop.services.ServiceUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class EntityCart implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "cart")
    @JsonProperty(value = "login")
    @JsonSerialize(using = SerializerUser.class)
    private EntityUser user;

    private String status = "NEW";
    @OneToMany
    private Set<EntityChosenGoods> chosenGoods;

    public void add(EntityChosenGoods g){
        chosenGoods.add(g);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntityUser getUser() {
        return user;
    }

    public void setUser(EntityUser user) {
        this.user = user;
    }

    public Set<EntityChosenGoods> getChosenGoods() {
        return chosenGoods;
    }

    public void setChosenGoods(Set<EntityChosenGoods> chosenGoods) {
        this.chosenGoods = chosenGoods;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
