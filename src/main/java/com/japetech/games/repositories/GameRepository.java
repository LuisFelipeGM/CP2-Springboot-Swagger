package com.japetech.games.repositories;

import com.japetech.games.models.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository  extends JpaRepository<GameModel, Long> {

    List<GameModel> findBynomeContainingIgnoreCase(String nome);
}
