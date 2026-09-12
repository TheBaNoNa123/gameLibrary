package com.gameLibrary.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class gameData {
    RestClient restClient = RestClient.create();

    @Value("${twitch.client_id}")
    private String clientId;

    TwitchToken cachedToken;

    public gameData(TwitchToken cachedToken) {
        this.cachedToken = cachedToken;
    }

    public List<gamesDTO> gamesData(String query){
        String token = cachedToken.twitchToken();

        String url = UriComponentsBuilder.fromUriString("https://api.igdb.com/v4/games")
                .toUriString();

        List<gamesDTO> response = restClient.post()
                .uri(url)
                .header("Client-ID", clientId)
                .header("Authorization", "Bearer " + token)
                .body(  "search \"" + query + "\";" +
                        "fields id, name, cover.url, total_rating, total_rating_count;" +
                        "where game_type = 0 & version_parent = null;" +
                        " limit 12;")
                .retrieve()
                .body(new ParameterizedTypeReference<List<gamesDTO>>() {
                });

        return response;
    }

}
