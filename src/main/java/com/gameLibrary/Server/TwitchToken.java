package com.gameLibrary.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;

@Service
public class TwitchToken {

    private final RestClient restClient = RestClient.create();

    @Value("${twitch.client_id}")
    private String clientId;

    @Value("${twitch.client_secret}")
    private String clientSecret;

    private String cachedToken;

    private Instant expiresAt;

    public String twitchToken(){
        if(cachedToken != null && Instant.now().isBefore(expiresAt)){
            return cachedToken;
        }

        String url = UriComponentsBuilder.fromUriString("https://id.twitch.tv/oauth2/token")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("grant_type", "client_credentials")
                .toUriString();

        TwitchTokenDTO response = restClient.post()
                .uri(url)
                .retrieve()
                .body(TwitchTokenDTO.class);

        this.cachedToken = response.getAccessToken();
        this.expiresAt = Instant.now().plusSeconds(response.getExpiresIn() - 60);

        return cachedToken;
    }
}
