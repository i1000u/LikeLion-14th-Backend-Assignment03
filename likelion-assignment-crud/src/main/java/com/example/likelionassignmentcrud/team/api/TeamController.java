package com.example.likelionassignmentcrud.team.api;


import com.example.likelionassignmentcrud.player.api.dto.response.PlayerListResponseDto;
import com.example.likelionassignmentcrud.player.application.PlayerService;
import com.example.likelionassignmentcrud.team.api.dto.request.TeamSaveRequestDto;
import com.example.likelionassignmentcrud.team.api.dto.request.TeamUpdateRequesetDto;
import com.example.likelionassignmentcrud.team.api.dto.response.TeamInfoResponseDto;
import com.example.likelionassignmentcrud.team.api.dto.response.TeamListResponseDto;
import com.example.likelionassignmentcrud.team.application.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
@Tag(name = "팀 API", description = "팀관리 API")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    //팀 저장
    @PostMapping
    @Operation(summary = "팀 정보 저장",description = "팀 정보 post")
    public ResponseEntity<String> teamSave(@RequestBody TeamSaveRequestDto teamSaveRequestDto) {
        teamService.teamSave(teamSaveRequestDto);
        return new ResponseEntity<>("팀 설정 완료", HttpStatus.CREATED);
    }

    //팀 정보 조회
    @Operation(summary = "팀 1개 조회",description = "팀 아이디 통해 조회")
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamInfoResponseDto> oneTeamFind(@PathVariable("teamId") Long teamId) {
        TeamInfoResponseDto teamInfoResponseDto = teamService.teamFindOne(teamId);
        return ResponseEntity.ok(teamInfoResponseDto);
    }

    //팀 전체 정보 조회
    @GetMapping
    @Operation(summary = "팀 전체 조회",description = "팀 전체 조회")
    public ResponseEntity<TeamListResponseDto> teamFind() {
        TeamListResponseDto teamListResponseDto = teamService.teamFindAll();
        return ResponseEntity.ok(teamListResponseDto);
    }

    //팀 내 소속 선수 조회
    @GetMapping("/{teamId}/players")
    @Operation(summary = "팀 내 소속 선수 조회",description = "팀 내 소속 선수 조회(소속 정보는 안뜨게 수정)")
    public ResponseEntity<PlayerListResponseDto> findPlayersByTeam(@PathVariable("teamId") Long teamId) {
        PlayerListResponseDto playerListResponseDto = playerService.playerFindAllByTeam(teamId);
        return ResponseEntity.ok(playerListResponseDto);

    }

    //수정
    @PatchMapping("/{teamId}")
    @Operation(summary = "팀 정보 수정",description = "아이디 통한, 정보 수정")
    public ResponseEntity<String> teamUpdate(
            @PathVariable("teamId") Long teamId, @RequestBody TeamUpdateRequesetDto teamUpdateRequesetDto) {
        teamService.teamUpdate(teamId, teamUpdateRequesetDto);
        return new ResponseEntity<>("팀 정보 수정", HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{teamId}")
    @Operation(summary = "팀 정보 삭제",description = "삭제")
    public ResponseEntity<String> teamDelete(
            @PathVariable("teamId") Long teamId){
        teamService.teamDelete(teamId);
        return new ResponseEntity<>("팀 해체",HttpStatus.OK);
        }

}


