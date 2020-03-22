package com.kvark900.api.configuration.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsRoleByName(RoleName name);
    Role findByName(RoleName name);
    Optional<Role> findById(Long id);
}
