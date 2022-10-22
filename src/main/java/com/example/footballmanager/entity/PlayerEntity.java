package com.example.footballmanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
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
}
