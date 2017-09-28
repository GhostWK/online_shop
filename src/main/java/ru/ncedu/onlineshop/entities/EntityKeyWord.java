package ru.ncedu.onlineshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class EntityKeyWord {
    @Id
    @GeneratedValue
    private Long id;

    private String k;
    private String v;

    @ManyToMany
    private Set<EntityGoods> goods;

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
}
