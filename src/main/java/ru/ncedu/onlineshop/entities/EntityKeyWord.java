package ru.ncedu.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class EntityKeyWord {
    @Id
    @GeneratedValue
    private Long id;

    private String k;
    private String v;
    @JsonIgnore
    @ManyToMany
    private Set<EntityGoods> goods;

    public EntityKeyWord add(EntityGoods x){
        if(goods == null) goods = new HashSet<>();
        goods.add(x);
        return this;
    }

    public EntityKeyWord(String k, String v) {
        this.k = k;
        this.v = v;
    }

    public EntityKeyWord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Set<EntityGoods> getGoods() {
        return goods;
    }

    public void setGoods(Set<EntityGoods> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%s, \"k\":\"%s\", \"v\":\"%s\"}", id, k, v);
    }
}
