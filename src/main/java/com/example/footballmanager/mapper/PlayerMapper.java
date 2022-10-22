package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.player.PlayerCreateDto;
import com.example.footballmanager.dto.player.PlayerDto;
import com.example.footballmanager.dto.player.PlayerUpdateDto;
import com.example.footballmanager.entity.PlayerEntity;
import org.mapstruct.Mapper;


import java.util.List;


@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerEntity toEntity(PlayerCreateDto dto);
    PlayerEntity toEntity(PlayerUpdateDto dto);
    PlayerDto toDto(PlayerEntity entity);
    List<PlayerDto> toDtoList(List<PlayerEntity> entities);
}