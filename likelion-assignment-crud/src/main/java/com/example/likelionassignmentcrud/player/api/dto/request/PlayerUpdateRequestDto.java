package com.example.likelionassignmentcrud.player.api.dto.request;

public record PlayerUpdateRequestDto(
        String name,
        String position,
        int uniformNumber,
        Long teamId
) {}
