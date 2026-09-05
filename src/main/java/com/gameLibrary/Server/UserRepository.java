package com.gameLibrary.Server;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String user);
    boolean existsByUsername(String username);
}
