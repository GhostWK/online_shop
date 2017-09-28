package ru.ncedu.onlineshop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EntityCart {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "cart")
    private EntityUser user;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
