package com.spring.dslist.services;

import com.spring.dslist.dto.GameDTO;
import com.spring.dslist.dto.GameMinDTO;
import com.spring.dslist.entities.Game;
import com.spring.dslist.repositories.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id)  {
        Game game = gameRepository.findById(id).get();
        return new GameDTO(game);
    }


}
