package com.example.footballmanager.entity.team;


import com.example.footballmanager.entity.player.PlayerEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;


import javax.persistence.*;
import java.util.List;


@Data
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


    public TeamEntity() {
    }

    public TeamEntity(Long id, String name, String city, String country, String coach, Long money, Long commission, List<PlayerEntity> players) {
        Id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.coach = coach;
        this.money = money;
        this.commission = commission;
        this.players = players;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }
}
