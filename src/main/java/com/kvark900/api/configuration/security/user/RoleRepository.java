package com.kvark900.api.configuration.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsRoleByName(RoleName name);
    Role findByName(RoleName name);
    Role findById(Long id);
}
