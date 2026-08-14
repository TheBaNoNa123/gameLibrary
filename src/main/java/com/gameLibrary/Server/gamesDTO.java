package com.gameLibrary.Server;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class gamesDTO {

    @Getter
    private String name;

    @Getter
    private String total_rating;

    @Getter
    private String total_rating_count;

    @Getter
    private Cover cover;

}
