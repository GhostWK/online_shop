package ru.ncedu.onlineshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ncedu.onlineshop.entities.EntityKeyWord;

import java.util.List;
import java.util.Optional;

public interface KeyWordRepository extends JpaRepository<EntityKeyWord, Long> {
    Optional<EntityKeyWord> getByKAndV(String k, String v);
}
