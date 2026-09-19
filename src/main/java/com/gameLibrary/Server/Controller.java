package com.gameLibrary.Server;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/server")
public class Controller {
    private final UserRepository userRepository;
    private final UserGamesRepository userGamesRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final registerUser registerUser;
    private final TwitchToken tokenService;
    private final gameData game;

    public Controller(UserRepository userRepository, UserGamesRepository userGamesRepository, JwtService jwtService, AuthenticationManager authManager, TwitchToken tokenService, gameData game, registerUser registerUser){
        this.userRepository = userRepository;
        this.userGamesRepository = userGamesRepository;
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

    @GetMapping("/auth/games")
    public List<gamesDTO> getGames(@RequestParam String query){
        return game.gamesData(query);
    }

    @PostMapping("/auth/savedGame")
    public void saveGames(@RequestBody SaveGameDTO savedGame, Authentication authentication){
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserGames userGames = new UserGames();

        userGames.setUser(user);
        userGames.setName(savedGame.getName());
        userGames.setCover(savedGame.getCover());
        userGamesRepository.save(userGames);

    }

    @PostMapping("/public/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerRequest){
        registerUser.register(registerRequest.getUsername(), registerRequest.getPassword());

        return ResponseEntity.ok("Register successful.");
    }

    @PostMapping("/public/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        String jwt = jwtService.generateToken(loginRequest.getUsername(), "USER");

        return ResponseEntity.ok(Map.of("token", jwt));
    }
}