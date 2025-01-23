package com.spring.dslist.controllers;

import com.spring.dslist.dto.GameDTO;
import com.spring.dslist.dto.GameMinDTO;
import com.spring.dslist.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(
            summary = "Get all games",
            description = "Retrieve a list of all games with minimal details.",
            tags = {"Games"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of games retrieved successfully.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GameMinDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<GameMinDTO> findAll() {
        return gameService.findAll();
    }

    @Operation(
            summary = "Get game by ID",
            description = "Retrieve full details of a specific game by its ID.",
            tags = {"Games"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Game details retrieved successfully.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GameDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public GameDTO findById(
            @Parameter(description = "ID of the game to retrieve.", required = true)
            @PathVariable Long id) {
        return gameService.findById(id);
    }
}
