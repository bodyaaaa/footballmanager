package com.example.footballmanager.service.team;


import com.example.footballmanager.dto.team.TeamCreateDto;
import com.example.footballmanager.dto.team.TeamDto;
import com.example.footballmanager.dto.team.TeamUpdateDto;

import java.util.List;

public interface TeamService {
    TeamDto create(TeamCreateDto teamDto);
    List<TeamDto> findAll();
    TeamDto findById(Long id);
    TeamDto update(Long id, TeamUpdateDto teamDto);
    void delete(Long id);
}
