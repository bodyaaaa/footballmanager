package com.example.footballmanager.controller;

import com.example.footballmanager.dto.team.TeamCreateDto;
import com.example.footballmanager.dto.team.TeamDto;
import com.example.footballmanager.dto.team.TeamUpdateDto;
import com.example.footballmanager.service.team.TeamServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("team/")
public class TeamController {
    private final TeamServiceImpl teamService;

    @GetMapping
    public List<TeamDto> findAll() {
        return teamService.findAll();
    }

    @GetMapping("{id}")
    public TeamDto findById(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDto create(@RequestBody TeamCreateDto team){
        return teamService.create(team);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TeamDto update(@PathVariable Long id, @RequestBody TeamUpdateDto team){
        return teamService.update(id, team);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        teamService.delete(id);
    }
}
