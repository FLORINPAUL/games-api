package com.games.games_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GamesRepository gamesRepository;

    @Autowired
    public GameService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public void Add(Game game) {
        gamesRepository.Add(game);
    }

    public Game GetById(Long id) {
        return gamesRepository.GetById(id);
    }

    public List<Game> GetAll() {
        return gamesRepository.GetAll();
    }

    public boolean Update(Game game) {
        try {
            gamesRepository.Update(game);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    public boolean Delete(Long id) {
        try {
            gamesRepository.DeleteById(id);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
}
