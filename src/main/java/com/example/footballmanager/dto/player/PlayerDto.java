package com.example.footballmanager.dto.player;

import com.example.footballmanager.entity.TeamEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlayerDto {
    private Long Id;
    private String name;
    private String surname;
    private String country;
    private Long age;
    private LocalDate startCareerDate;
    private TeamEntity team;

}
