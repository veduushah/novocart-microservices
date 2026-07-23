package com.novacart.auth.dto;

public record AuthResponse(
    String token,
    UserDto user
) {}
