package com.msbeigi.request;

public record UpdateCartItemRequest(
        Long cartItemId,
        Integer quantity
) {
}
