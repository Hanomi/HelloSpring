package io.shifu.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.shifu.project1.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
