package io.shifu.project1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import io.shifu.project1.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
}
