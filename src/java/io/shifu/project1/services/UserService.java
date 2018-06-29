package io.shifu.project1.services;

import io.shifu.project1.model.User;

public interface UserService {

    void save(User user);

    void saveVk(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    void activationUser(User user);

    User findByVkId(Long id);
}
