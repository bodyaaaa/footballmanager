package com.example.footballmanager.service.team;

import com.example.footballmanager.dto.team.TeamCreateDto;
import com.example.footballmanager.dto.team.TeamDto;
import com.example.footballmanager.dto.team.TeamUpdateDto;
import com.example.footballmanager.entity.TeamEntity;
import com.example.footballmanager.exception.NotFoundException;
import com.example.footballmanager.mapper.TeamMapper;
import com.example.footballmanager.repository.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final String TEAM_NOT_FOUND = "Team not found";

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDto> findAll() {
        List<TeamEntity> teamEntities = teamRepository.findAll();
        return teamMapper.toDtoList(teamEntities);
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDto findById(Long id) {
        TeamEntity teamEntity = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(TEAM_NOT_FOUND));
        return teamMapper.toDto(teamEntity);
    }

    @Override
    @Transactional
    public TeamDto create(TeamCreateDto teamCreateDto){
        TeamEntity teamEntity = teamMapper.toEntity(teamCreateDto);
        teamEntity =  teamRepository.save(teamEntity);
        return teamMapper.toDto(teamEntity);
    }

    @Override
    @Transactional
    public TeamDto update(Long id, TeamUpdateDto teamUpdateDto){
        TeamEntity teamEntity = teamMapper.toEntity(teamUpdateDto);
        teamEntity.setId(id);
        teamRepository.save(teamEntity);
        return teamMapper.toDto(teamEntity);
    }

    @Override
    @Transactional
    public void delete(Long id){
        teamRepository.deleteById(id);
    }
}
