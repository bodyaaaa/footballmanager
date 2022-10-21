package com.example.footballmanager.service.team;

import com.example.footballmanager.entity.team.TeamEntity;
import com.example.footballmanager.exception.NotFoundException;
import com.example.footballmanager.repository.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<TeamEntity> findAll() {
        List<TeamEntity> list = new ArrayList<>();
        Iterable<TeamEntity> teams = teamRepository.findAll();
        teams.forEach(list::add);
        return list;
    }

    public TeamEntity findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Team not found"));
    }

    public TeamEntity create(TeamEntity team){
        return teamRepository.save(team);
    }

    @Transactional
    public void update(Long id, TeamEntity team){
        int affectedRows =  teamRepository.update(id, team);
        if (affectedRows == 0){
            throw new NotFoundException("Team not found");
        }
    }

    public void delete(Long id){
        teamRepository.deleteById(id);
    }
}
