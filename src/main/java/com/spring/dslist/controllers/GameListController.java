package com.spring.dslist.controllers;

import com.spring.dslist.dto.GameDTO;
import com.spring.dslist.dto.GameListDTO;
import com.spring.dslist.dto.GameMinDTO;
import com.spring.dslist.services.GameListService;
import com.spring.dslist.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @Operation(summary = "Get all game lists", description = "Retrieve a list of all game collections available.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of game collections retrieved successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }

    @Operation(summary = "Get games by list ID", description = "Retrieve a list of minimal game details for a specific collection.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Games retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "Game list not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{listId}/games")
    public List<GameMinDTO> findByList(
            @Parameter(description = "ID of the game list to retrieve games for.", required = true, example = "1")
            @PathVariable Long listId) {
        return gameService.findByList(listId);
    }

}
