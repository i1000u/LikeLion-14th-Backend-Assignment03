package com.example.likelionassignmentcrud.player.api;

import com.example.likelionassignmentcrud.player.api.dto.request.PlayerSaveRequestDto;
import com.example.likelionassignmentcrud.player.api.dto.request.PlayerUpdateRequestDto;
import com.example.likelionassignmentcrud.player.api.dto.response.PlayerDetailResponseDto;
import com.example.likelionassignmentcrud.player.application.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
@Tag(name = "선수 API", description = "선수관리 API")
public class PlayerController {
    private final PlayerService playerService;

    //선수 저장
    @PostMapping
    @Operation(summary = "선수 정보 저장",description = "")
    public ResponseEntity<String> playerSave(@RequestBody PlayerSaveRequestDto playerSaveRequestDto) {
        playerService.playerSave(playerSaveRequestDto);
        return new ResponseEntity<>("선수 정보가 등록되었습니다.", HttpStatus.CREATED);
    }

    //선수 조회
    @GetMapping("/{playerId}")
    @Operation(summary = "선수 1명 조회",description = "")
    public ResponseEntity<PlayerDetailResponseDto> playerFind(@PathVariable("playerId") Long playerId) {
        PlayerDetailResponseDto playerDetailResponseDto = playerService.playerFind(playerId);
        return ResponseEntity.ok(playerDetailResponseDto);
    }


    //수정
    @PatchMapping("/{playerId}")
    @Operation(summary = "선수 정보 수정",description = "")
    public ResponseEntity<String> playerUpdate(
            @PathVariable("playerId") Long playerId, @RequestBody PlayerUpdateRequestDto playerUpdateRequestDto){
        playerService.playerUpdate(playerId, playerUpdateRequestDto);
        return new ResponseEntity<>("선수 정보 수정",HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{playerId}")
    @Operation(summary = "선수 정보 삭제",description = "")
    public ResponseEntity<String> playerDelete(@PathVariable("playerId") Long playerId) {
        playerService.playerDelete(playerId);
        return new ResponseEntity<>( "해당 선수 말소",HttpStatus.OK);
    }
}
