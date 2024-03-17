package com.msbeigi.request;

import com.msbeigi.model.Address;
import com.msbeigi.model.ContactInformation;

import java.util.List;

public record CreateRestaurantRequest(
        Long id,
        String name,
        String description,
        String cuisineType,
        Address address,
        ContactInformation contactInformation,
        String openingHours,
        List<String> images) {
}
