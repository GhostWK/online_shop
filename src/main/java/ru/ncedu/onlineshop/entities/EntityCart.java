package ru.ncedu.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class EntityCart implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @JsonIgnore
    @OneToOne(mappedBy = "cart")
    private EntityUser user;

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
}
