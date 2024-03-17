package com.msbeigi.request;

import java.util.List;

public record AddCartItemRequest(
        Long foodId,
        Integer quantity,
        List<String> ingredients
) {
}
