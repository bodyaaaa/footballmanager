package com.example.footballmanager.controller.player;

import com.example.footballmanager.entity.player.PlayerEntity;
import com.example.footballmanager.service.player.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("player")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public List<PlayerEntity> findAll() {
        return playerService.findAll();
    }

    @GetMapping("{id}")
    public PlayerEntity findById(@PathVariable Long id) {
        return playerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerEntity create(@RequestBody PlayerEntity player){
        return playerService.create(player);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody PlayerEntity player){
        playerService.update(id, player);
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
