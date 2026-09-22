package com.gameLibrary.Server;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGamesRepository extends JpaRepository<UserGames, Long> {
    List<UserGames> findByUser(User user);
}
