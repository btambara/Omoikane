package com.tambara.omoikane.gateway.repository;

import com.tambara.omoikane.gateway.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String username);
}
