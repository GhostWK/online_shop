package ru.ncedu.onlineshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ncedu.onlineshop.entities.EntityUser;

import java.util.Optional;

public interface RepositoryUser extends JpaRepository<EntityUser, Long> {

    Optional<EntityUser> findByLogin(String login);

}
