package com.gameLibrary.Server;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/server")
public class Controller {

    private final TwitchToken tokenService;

    private final gameData game;

    public Controller(TwitchToken tokenService, gameData game){
        this.tokenService = tokenService;
        this.game = game;
    }

    @GetMapping("/token")
    public String getToken(){
        return tokenService.twitchToken();
    }

    @GetMapping("/games")
    public List<gamesDTO> getGames(){
        return game.gamesData();
    }
}
