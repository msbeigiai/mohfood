package com.msbeigi.response;

import com.msbeigi.model.USER_ROLE;

public record AuthResponse(String jwt, String message, USER_ROLE role) {
}
