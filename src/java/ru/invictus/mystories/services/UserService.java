package ru.invictus.mystories.services;

import ru.invictus.mystories.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
