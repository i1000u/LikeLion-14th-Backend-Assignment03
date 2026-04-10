    package com.example.likelionassignmentcrud.player.application;

    import com.example.likelionassignmentcrud.player.api.dto.request.PlayerSaveRequestDto;
    import com.example.likelionassignmentcrud.player.api.dto.request.PlayerUpdateRequestDto;
    import com.example.likelionassignmentcrud.player.api.dto.response.PlayerDetailResponseDto;
    import com.example.likelionassignmentcrud.player.api.dto.response.PlayerInfoResponseDto;
    import com.example.likelionassignmentcrud.player.api.dto.response.PlayerListResponseDto;
    import com.example.likelionassignmentcrud.player.domain.Player;
    import com.example.likelionassignmentcrud.player.domain.repository.PlayerRepository;
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
    @Transactional(readOnly = true)
    public class PlayerService {
        private final PlayerRepository playerRepository;
        private final TeamRepository teamRepository;

        // 저장
        @Transactional
        public void playerSave(PlayerSaveRequestDto playerSaveRequestDto) {
            Team team = teamRepository.findById(playerSaveRequestDto.teamId()).orElseThrow(IllegalArgumentException::new);

            Player player = Player.builder()
                    .name(playerSaveRequestDto.name())
                    .uniformNumber(playerSaveRequestDto.uniformNumber())
                    .position(playerSaveRequestDto.position())
                    .team(team)
                    .build();


            playerRepository.save(player);
        }
        // 특정 선수 조회
        public PlayerDetailResponseDto playerFind(Long playerId) {
            Player player = playerRepository.findById(playerId).orElseThrow(IllegalArgumentException::new);
            return PlayerDetailResponseDto.from(player);
        }

        // 특정 팀의 선수 전체 조회
        public PlayerListResponseDto playerFindAllByTeam(Long teamId) {

            Team team = teamRepository.findById(teamId).orElseThrow(IllegalArgumentException::new);
            List<Player> players = playerRepository.findByTeam(team);

            List<PlayerInfoResponseDto> playerInfoResponseDtos = players.stream()
                    .map(PlayerInfoResponseDto::from)
                    .toList();

            return PlayerListResponseDto.from(team.getName(), playerInfoResponseDtos);
        }


        //수정
        @Transactional
        public void playerUpdate(Long playerId, PlayerUpdateRequestDto playerUpdateRequestDto) {
            Player player = playerRepository.findById(playerId).orElseThrow(IllegalArgumentException::new);
            player.update(playerUpdateRequestDto);
        }

        //삭제
        @Transactional
        public void playerDelete(Long playerId) {
            Player player = playerRepository.findById(playerId).orElseThrow(IllegalArgumentException::new);
            playerRepository.delete(player);
        }


    }
