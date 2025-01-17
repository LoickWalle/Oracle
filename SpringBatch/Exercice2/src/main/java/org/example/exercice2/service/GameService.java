package org.example.exercice2.service;

import org.example.exercice2.entity.Game;
import org.example.exercice2.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(int id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    public boolean deleteGameById(int id) {
        if(gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
