package com.msbeigi.request;

import com.msbeigi.model.Address;

public record OrderRequest(
        Long restaurantId,
        Address deliveryAddress
) {
}
