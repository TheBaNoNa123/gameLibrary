package com.gameLibrary.Server;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGamesRepository extends JpaRepository<UserGames, Long> {
}
