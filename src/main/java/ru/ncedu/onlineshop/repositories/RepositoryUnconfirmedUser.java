package ru.ncedu.onlineshop.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ncedu.onlineshop.entities.EntityUnconfirmedUser;

import java.util.Optional;

public interface RepositoryUnconfirmedUser extends CrudRepository<EntityUnconfirmedUser, Long> {
    Optional<EntityUnconfirmedUser> getByLogin(String login);
    Optional<EntityUnconfirmedUser> getByHashKey(String hashKey);
}
