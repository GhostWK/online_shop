package ru.ncedu.onlineshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ncedu.onlineshop.entities.EntityGoods;

import java.util.List;
import java.util.Optional;


public interface GoodsRepository extends /*JpaRepository<EntityGoods, Long>*/ PagingAndSortingRepository<EntityGoods, Long> {

    Optional<EntityGoods> getByName(String name);
    Optional<EntityGoods> getById(Long id);
}
