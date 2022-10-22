package com.example.footballmanager.service.player;

import com.example.footballmanager.dto.player.PlayerCreateDto;
import com.example.footballmanager.dto.player.PlayerDto;
import com.example.footballmanager.dto.player.PlayerUpdateDto;
import com.example.footballmanager.entity.PlayerEntity;
import com.example.footballmanager.entity.TeamEntity;
import com.example.footballmanager.exception.NotFoundException;
import com.example.footballmanager.mapper.PlayerMapper;
import com.example.footballmanager.repository.player.PlayerRepository;
import com.example.footballmanager.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;
    private final String PLAYER_NOT_FOUND = "Player not found";
    private final String TEAM_NOT_FOUND = "Team not found";
    private final String NOT_ENOUGH_MONEY = "Team doesn't have enough money";

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDto> findAll() {
        List<PlayerEntity> playerEntities = playerRepository.findAll();
        return playerMapper.toDtoList(playerEntities);
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerDto findById(Long id) {
            PlayerEntity playerEntity = playerRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(PLAYER_NOT_FOUND));
            return playerMapper.toDto(playerEntity);
    }

    @Override
    @Transactional
    public PlayerDto create(PlayerCreateDto playerCreateDto){
        PlayerEntity playerEntity = playerMapper.toEntity(playerCreateDto);
        playerEntity =  playerRepository.save(playerEntity);
        return playerMapper.toDto(playerEntity);
    }

    @Override
    @Transactional
    public PlayerDto update(Long id, PlayerUpdateDto playerUpdateDto){
        PlayerEntity playerEntity = playerMapper.toEntity(playerUpdateDto);
        playerEntity.setId(id);
        playerRepository.save(playerEntity);
        return playerMapper.toDto(playerEntity);
    }

    @Override
    @Transactional
    public void delete(Long id){
        playerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void transfer(Long playerId, Long newTeamId){
        PlayerEntity player = playerRepository.findById(playerId)
                .orElseThrow(() ->  new NotFoundException(PLAYER_NOT_FOUND));
        TeamEntity oldTeam = player.getTeam();
        TeamEntity newTeam = teamRepository.findById(newTeamId)
                .orElseThrow(() ->  new NotFoundException(TEAM_NOT_FOUND));
        Long months = ChronoUnit.MONTHS.between(player.getStartCareerDate(), LocalDate.now());
        Long price = (100000 / player.getAge()) * months;
        Long commission  = oldTeam.getCommission();
        price += Math.round(commission * 0.01 * price);
        if (price <= newTeam.getMoney()) {
            player.setTeam(newTeam);
            playerRepository.save(player);
            oldTeam.setMoney(oldTeam.getMoney() + price);
            teamRepository.save(oldTeam);
            newTeam.setMoney(newTeam.getMoney() - price);
            teamRepository.save(newTeam);
        }
        else{
            throw new RuntimeException(NOT_ENOUGH_MONEY);
        }
    }
}