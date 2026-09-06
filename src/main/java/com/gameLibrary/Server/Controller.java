package com.gameLibrary.Server;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/server")
public class Controller {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final registerUser registerUser;
    private final TwitchToken tokenService;
    private final gameData game;

    public Controller(JwtService jwtService, AuthenticationManager authManager, TwitchToken tokenService, gameData game, registerUser registerUser){
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.registerUser = registerUser;
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
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerRequest){
        registerUser.register(registerRequest.getUsername(), registerRequest.getPassword());

        return ResponseEntity.ok("Register successful.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        String jwt = jwtService.generateToken(loginRequest.getUsername(), "USER");

        return ResponseEntity.ok(Map.of("token", jwt));
    }
}