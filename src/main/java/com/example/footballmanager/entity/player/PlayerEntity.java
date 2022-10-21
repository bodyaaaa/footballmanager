package com.example.footballmanager.entity.player;

import com.example.footballmanager.entity.team.TeamEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "player")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String surname;
    private String country;
    private Long age;
    private LocalDate startCareerDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private TeamEntity team;

    public PlayerEntity() {
    }

    public PlayerEntity(Long id, String name, String surname, String country, Long age, LocalDate startCareerDate, TeamEntity team) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.age = age;
        this.startCareerDate = startCareerDate;
        this.team = team;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public LocalDate getStartCareerDate() {
        return startCareerDate;
    }

    public void setStartCareerDate(LocalDate startCareerDate) {
        this.startCareerDate = startCareerDate;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }
}
