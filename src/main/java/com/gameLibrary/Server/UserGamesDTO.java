package com.gameLibrary.Server;

import lombok.Data;

@Data
public class UserGamesDTO {
    private String name;
    private String cover;
    private Integer rating;
    private String review;

    public UserGamesDTO(String name, String cover, Integer rating,String review){
        this.name = name;
        this.cover = cover;
        this.rating = rating;
        this.review = review;
    }
}
