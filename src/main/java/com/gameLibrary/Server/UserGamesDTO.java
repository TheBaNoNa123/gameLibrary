package com.gameLibrary.Server;

import lombok.Data;

@Data
public class UserGamesDTO {
    private String name;
    private String cover;

    public UserGamesDTO(String name, String cover){
        this.name = name;
        this.cover = cover;

    }
}
