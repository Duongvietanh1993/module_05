package com.mosft.repository;

import com.mosft.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UseRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
