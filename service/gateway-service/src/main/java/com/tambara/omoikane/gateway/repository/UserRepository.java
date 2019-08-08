package com.tambara.omoikane.gateway.repository;

import com.tambara.omoikane.gateway.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
