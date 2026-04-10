package com.example.likelionassignmentcrud.player.api.dto.request;


public record PlayerSaveRequestDto (
        String name,
        String position,
        int uniformNumber,
        Long teamId
){}
