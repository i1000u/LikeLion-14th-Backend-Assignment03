package com.example.likelionassignmentcrud.player.api.dto.response;

import com.example.likelionassignmentcrud.player.domain.Player;
import lombok.Builder;

@Builder
public record PlayerInfoResponseDto(
        Long playerId,
        String name,
        String position,
        int uniformNumber
) {
    public static PlayerInfoResponseDto from(Player player) {
        return PlayerInfoResponseDto.builder()
                .playerId(player.getPlayerId())
                .name(player.getName())
                .position(player.getPosition())
                .uniformNumber(player.getUniformNumber())
                .build();
    }
}