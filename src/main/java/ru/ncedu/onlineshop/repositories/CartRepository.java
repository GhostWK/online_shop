package ru.ncedu.onlineshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ncedu.onlineshop.entities.EntityCart;

public interface CartRepository extends JpaRepository<EntityCart, Long> {
}
