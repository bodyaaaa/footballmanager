package com.example.footballmanager.controller.team;

import com.example.footballmanager.entity.team.TeamEntity;
import com.example.footballmanager.service.team.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("team")
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public List<TeamEntity> findAll() {
        return teamService.findAll();
    }

    @GetMapping("{id}")
    public TeamEntity findById(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamEntity create(@RequestBody TeamEntity team){
        return teamService.create(team);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody TeamEntity team){
        teamService.update(id, team);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        teamService.delete(id);
    }
}
