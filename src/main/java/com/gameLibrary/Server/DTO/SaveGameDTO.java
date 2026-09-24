package com.gameLibrary.Server.DTO;

import lombok.Data;

@Data
public class SaveGameDTO {
    private String name;
    private String cover;
    private Integer rating;
    private String review;
}
