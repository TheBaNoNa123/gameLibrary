package com.gameLibrary.Server.Controllers;


import com.gameLibrary.Server.Entity.User;
import com.gameLibrary.Server.Entity.UserGames;
import com.gameLibrary.Server.IGDB.TwitchToken;
import com.gameLibrary.Server.IGDB.gameData;
import com.gameLibrary.Server.JwtUtil.JwtService;
import com.gameLibrary.Server.Repository.UserGamesRepository;
import com.gameLibrary.Server.Repository.UserRepository;
import com.gameLibrary.Server.Security.registerUser;
import com.gameLibrary.Server.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/auth/games")
    public List<gamesDTO> getGames(@RequestParam String query){
        return game.gamesData(query);
    }

    @GetMapping("/auth/userProfileGames")
    public List<UserGamesDTO> getUserGames(Authentication authentication){
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userGamesRepository.findByUser(user).stream()
                .map(game -> new UserGamesDTO(game.getName(), game.getCover(), game.getRating(),
                        game.getReview()))
                .toList();
    }

    @PostMapping("/auth/savedGame")
    public void saveGames(@RequestBody SaveGameDTO savedGame, Authentication authentication){
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserGames userGames = new UserGames();
        userGames.setUser(user);

        if(!userGamesRepository.existsByNameAndUser(savedGame.getName(), user)){
            userGames.setName(savedGame.getName());
            userGames.setCover(savedGame.getCover());
            userGamesRepository.save(userGames);
        }

        Optional<UserGames> result = userGamesRepository.findByUserAndName(user, savedGame.getName());
        if(result.isPresent()){
            userGames = result.get();
            System.out.println(savedGame.getRating());
            System.out.println(savedGame.getReview());
            userGames.setRating(savedGame.getRating());
            userGames.setReview(savedGame.getReview());
            userGamesRepository.save(userGames);
        }

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

    @DeleteMapping("/auth/deleteGame")
    public void deleteGame(@RequestParam String gameName, Authentication authentication){
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserGames userGames = new UserGames();
        userGames.setUser(user);

        Optional<UserGames> result = userGamesRepository.findByUserAndName(user, gameName);
        if(result.isPresent()){
            userGames = result.get();
            userGamesRepository.delete(userGames);
        }


    }
}