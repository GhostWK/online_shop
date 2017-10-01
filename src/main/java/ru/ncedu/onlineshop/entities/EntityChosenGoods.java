package ru.ncedu.onlineshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EntityChosenGoods {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private EntityGoods goods;
    private Integer amount;

    public EntityChosenGoods() {
    }

    public EntityChosenGoods(EntityGoods goods, Integer amount) {
        this.goods = goods;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntityGoods getGoods() {
        return goods;
    }

    public void setGoods(EntityGoods goods) {
        this.goods = goods;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
