package com.example.footballmanager.repository.player;

import com.example.footballmanager.entity.player.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    @Modifying
    @Query("""
            UPDATE PlayerEntity SET 
                name = :#{#entity.name},
                surname = :#{#entity.surname},
                country = :#{#entity.country},
                age = :#{#entity.age},
                startCareerDate = :#{#entity.startCareerDate},
                team = :#{#entity.team}
            WHERE Id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") PlayerEntity entity);
}
