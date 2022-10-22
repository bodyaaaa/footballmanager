package com.example.footballmanager.service.player;

import com.example.footballmanager.dto.player.PlayerCreateDto;
import com.example.footballmanager.dto.player.PlayerDto;
import com.example.footballmanager.dto.player.PlayerUpdateDto;

import java.util.List;

public interface PlayerService {
    PlayerDto create(PlayerCreateDto playerDto);
    List<PlayerDto> findAll();
    PlayerDto findById(Long id);
    PlayerDto update(Long id, PlayerUpdateDto playerDto);
    void delete(Long id);
    void transfer(Long playerId, Long teamId);
}
