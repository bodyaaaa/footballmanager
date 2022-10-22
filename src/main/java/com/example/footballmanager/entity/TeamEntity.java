package com.example.footballmanager.entity;


import com.example.footballmanager.entity.PlayerEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "team")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String city;
    private String country;
    private String coach;
    private Long money;
    private Long commission;

    @JsonManagedReference
    @OneToMany(mappedBy="team")
    private List<PlayerEntity> players;
}
