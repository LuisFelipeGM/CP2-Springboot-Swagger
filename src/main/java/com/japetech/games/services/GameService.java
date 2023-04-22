package com.japetech.games.services;

import com.japetech.games.models.GameModel;
import com.japetech.games.repositories.GameRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService extends MyService<GameModel, Long>{

    private final GameRepository gameRepository;

    GameService(JpaRepository<GameModel, Long> repository, GameRepository gameRepository){
        super(repository);
        this.gameRepository = gameRepository;
    }

    public List<GameModel> findBynomeContainingIgnoreCase(String nome){
        return ((GameRepository) repository).findBynomeContainingIgnoreCase(nome);
    }

}
