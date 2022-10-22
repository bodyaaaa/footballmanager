package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.team.TeamCreateDto;
import com.example.footballmanager.dto.team.TeamDto;
import com.example.footballmanager.dto.team.TeamUpdateDto;
import com.example.footballmanager.entity.TeamEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamEntity toEntity(TeamCreateDto dto);
    TeamEntity toEntity(TeamUpdateDto dto);
    TeamDto toDto(TeamEntity entity);
    List<TeamDto> toDtoList(List<TeamEntity> entities);
}