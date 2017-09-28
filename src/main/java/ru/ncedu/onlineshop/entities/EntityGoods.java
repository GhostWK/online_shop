package ru.ncedu.onlineshop.entities;

import ru.ncedu.onlineshop.wrappers.WrapperGoods;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class EntityGoods {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer quantity;
    private Double price;

    @ManyToMany
    private Set<EntityKeyWord> keyWords;

    public EntityGoods() {
    }

    public EntityGoods(String name, Integer quantity, Double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public EntityGoods(WrapperGoods g) {
        this(g.getName(), g.getQuantity(),g.getPrice());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<EntityKeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(Set<EntityKeyWord> keyWords) {
        this.keyWords = keyWords;
    }
}
