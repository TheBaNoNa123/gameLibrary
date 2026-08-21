package com.gameLibrary.Server;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public void register(@RequestBody RegisterDTO registerRequest){

    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDTO loginRequest){}
}
