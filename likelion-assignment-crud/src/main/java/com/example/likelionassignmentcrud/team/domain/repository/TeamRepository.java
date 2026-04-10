package com.example.likelionassignmentcrud.team.domain.repository;

import com.example.likelionassignmentcrud.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
