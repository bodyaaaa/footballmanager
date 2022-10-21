package com.example.footballmanager.service.player;

import com.example.footballmanager.entity.player.PlayerEntity;
import com.example.footballmanager.entity.team.TeamEntity;
import com.example.footballmanager.exception.NotFoundException;
import com.example.footballmanager.repository.player.PlayerRepository;
import com.example.footballmanager.repository.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService{
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public List<PlayerEntity> findAll() {
        List<PlayerEntity> list = new ArrayList<>();
        Iterable<PlayerEntity> players = playerRepository.findAll();
        players.forEach(list::add);
        return list;
    }

    public PlayerEntity findById(Long id) {
            return playerRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Player not found"));
    }

    public PlayerEntity create(PlayerEntity player){
        return playerRepository.save(player);
    }

    @Transactional
    public void update(Long id, PlayerEntity player){
        int affectedRows =  playerRepository.update(id, player);
        if (affectedRows == 0){
            throw new NotFoundException("Player not found");
        }
    }

    public void delete(Long id){
        playerRepository.deleteById(id);
    }

    @Transactional
    public void transfer(Long playerId, Long newTeamId){
        PlayerEntity player = playerRepository.findById(playerId)
                .orElseThrow(() ->  new NotFoundException("Player not found"));
        TeamEntity oldTeam = player.getTeam();
        TeamEntity newTeam = teamRepository.findById(newTeamId)
                .orElseThrow(() ->  new NotFoundException("Team not found"));
        Long months = ChronoUnit.MONTHS.between(player.getStartCareerDate(), LocalDate.now());
        Long price = (100000 / player.getAge()) * months;
        Long commission  = oldTeam.getCommission();
        price += Math.round(commission * 0.01 * price);
        if (price <= newTeam.getMoney()) {
            player.setTeam(newTeam);
            playerRepository.update(playerId, player);
            oldTeam.setMoney(oldTeam.getMoney() + price);
            teamRepository.update(oldTeam.getId(), oldTeam);
            newTeam.setMoney(newTeam.getMoney() - price);
            teamRepository.update(newTeamId, newTeam);
        }
        else{
            throw new RuntimeException("Team doesn't have enough money");
        }
    }
}