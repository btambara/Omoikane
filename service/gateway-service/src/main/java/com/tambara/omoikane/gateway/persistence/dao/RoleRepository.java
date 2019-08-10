package com.tambara.omoikane.gateway.persistence.dao;

import com.tambara.omoikane.gateway.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String username);
}
