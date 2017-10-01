package ru.ncedu.onlineshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ncedu.onlineshop.entities.EntityChosenGoods;

public interface ChosenGoodsRepository extends JpaRepository<EntityChosenGoods, Long>{

}
