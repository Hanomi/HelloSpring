package io.shifu.project1.repository;

import io.shifu.project1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
}
