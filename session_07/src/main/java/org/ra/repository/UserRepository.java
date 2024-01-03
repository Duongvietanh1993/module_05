package org.ra.repository;

import org.ra.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByUsername(String username);
}
