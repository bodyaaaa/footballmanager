package com.example.footballmanager.repository.team;

import com.example.footballmanager.entity.team.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    @Modifying
    @Query("""
            UPDATE TeamEntity SET 
                name = :#{#entity.name},
                city = :#{#entity.city},
                country = :#{#entity.country},
                coach = :#{#entity.coach},
                money = :#{#entity.money},
                commission = :#{#entity.commission}
            WHERE Id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") TeamEntity entity);
}
