package com.example.footballmanager.controller;

import com.example.footballmanager.dto.player.PlayerCreateDto;
import com.example.footballmanager.dto.player.PlayerDto;
import com.example.footballmanager.dto.player.PlayerUpdateDto;
import com.example.footballmanager.service.player.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("player/")
public class PlayerController {
    private final PlayerServiceImpl playerService;

    @Autowired
    public PlayerController(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDto> findAll() {
        return playerService.findAll();
    }

    @GetMapping("{id}")
    public PlayerDto findById(@PathVariable Long id) {
        return playerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDto create(@RequestBody PlayerCreateDto player){
        return playerService.create(player);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PlayerDto update(@PathVariable Long id, @RequestBody PlayerUpdateDto player){
        return playerService.update(id, player);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        playerService.delete(id);
    }

    @PutMapping("{playerId}/transfer/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@PathVariable Long playerId, @PathVariable Long teamId) {playerService.transfer(playerId, teamId);}
}
