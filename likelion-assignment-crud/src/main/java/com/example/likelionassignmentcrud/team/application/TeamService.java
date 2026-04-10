package com.example.likelionassignmentcrud.team.application;

import com.example.likelionassignmentcrud.team.api.dto.request.TeamSaveRequestDto;
import com.example.likelionassignmentcrud.team.api.dto.request.TeamUpdateRequesetDto;
import com.example.likelionassignmentcrud.team.api.dto.response.TeamInfoResponseDto;
import com.example.likelionassignmentcrud.team.api.dto.response.TeamListResponseDto;
import com.example.likelionassignmentcrud.team.domain.Team;
import com.example.likelionassignmentcrud.team.domain.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional( readOnly = true)
public class TeamService {
    private final TeamRepository teamRepository;

    //팀 정보 저장
    @Transactional
    public void teamSave(TeamSaveRequestDto teamSaveRequestDto) {
        Team team = Team.builder()
                .location(teamSaveRequestDto.location())
                .name(teamSaveRequestDto.name())
                .build();
        teamRepository.save(team);
    }

    //단일 팀 조회
    public TeamInfoResponseDto teamFindOne(Long teamId) {
        Team team = teamRepository
                .findById(teamId).orElseThrow(IllegalArgumentException::new);
        return TeamInfoResponseDto.from(team);
    }

    //팀 모두 조회
    public TeamListResponseDto teamFindAll() {
        List<Team> teams = teamRepository.findAll();
        if (teams.isEmpty()) {
            throw new IllegalArgumentException("팀이 없습니다.");
        }
        List<TeamInfoResponseDto> teamInfoResponseDtoList = teams.stream()
                .map(TeamInfoResponseDto::from)
                .toList();
    return TeamListResponseDto.from(teamInfoResponseDtoList);

    }

    //수정
    @Transactional
    public void teamUpdate(Long teamId, TeamUpdateRequesetDto teamUpdateRequesetDto) {
     Team team =teamRepository.findById(teamId).orElseThrow(IllegalArgumentException::new);
    team.update(teamUpdateRequesetDto);
    }

    //삭제
    @Transactional
    public void teamDelete(Long teamId) {
    Team team= teamRepository.findById(teamId).orElseThrow(IllegalArgumentException::new);
    teamRepository.delete(team);
    }



}
