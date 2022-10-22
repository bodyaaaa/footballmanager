package com.example.footballmanager.dto.team;

import com.example.footballmanager.entity.PlayerEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamUpdateDto {
    private String name;
    private String city;
    private String country;
    private String coach;
    private Long money;
    private Long commission;
    private List<PlayerEntity> players;
}
