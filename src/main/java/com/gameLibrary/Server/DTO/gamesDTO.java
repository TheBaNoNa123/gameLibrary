package com.gameLibrary.Server.DTO;

import com.gameLibrary.Server.IGDB.Cover;
import lombok.Getter;

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
