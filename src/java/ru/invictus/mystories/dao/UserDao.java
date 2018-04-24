package ru.invictus.mystories.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.invictus.mystories.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
