package com.example.likelionassignmentcrud.player.domain.repository;

import com.example.likelionassignmentcrud.player.domain.Player;
import com.example.likelionassignmentcrud.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeam(Team team);
}
