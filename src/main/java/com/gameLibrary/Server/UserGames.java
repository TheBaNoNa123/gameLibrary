package com.gameLibrary.Server;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_games")
public class UserGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String cover;
}
