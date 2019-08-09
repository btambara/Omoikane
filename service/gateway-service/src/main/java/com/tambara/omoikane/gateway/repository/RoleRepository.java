package com.tambara.omoikane.gateway.repository;

import com.tambara.omoikane.gateway.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String username);
}
