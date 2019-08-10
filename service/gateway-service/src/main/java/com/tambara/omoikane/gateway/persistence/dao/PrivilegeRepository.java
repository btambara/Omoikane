package com.tambara.omoikane.gateway.persistence.dao;

import com.tambara.omoikane.gateway.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String username);
}
