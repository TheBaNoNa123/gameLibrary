package com.gameLibrary.Server;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserGamesRepository extends JpaRepository<UserGames, Long> {
    List<UserGames> findByUser(User user);
    Optional<UserGames> findByUserAndName(User user, String name);
    boolean existsByNameAndUser(String name, User user);
}
