package ru.invictus.mystories.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.invictus.mystories.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
}
