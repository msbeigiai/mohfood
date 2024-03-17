package com.msbeigi.service.impl;

import com.msbeigi.dto.RestaurantDto;
import com.msbeigi.model.Address;
import com.msbeigi.model.Restaurant;
import com.msbeigi.model.User;
import com.msbeigi.repository.AddressRepository;
import com.msbeigi.repository.RestaurantRepository;
import com.msbeigi.repository.UserRepository;
import com.msbeigi.request.CreateRestaurantRequest;
import com.msbeigi.service.RestaurantService;
import com.msbeigi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {

        Address address = addressRepository.save(request.address());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(request.contactInformation());
        restaurant.setCuisineType(request.cuisineType());
        restaurant.setDescription(request.description());
        restaurant.setImages(request.images());
        restaurant.setName(request.name());
        restaurant.setOpeningHours(request.openingHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurantRequest) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updateRestaurantRequest.cuisineType());
        }

        if (restaurant.getDescription() != null) {
            restaurant.setDescription(updateRestaurantRequest.description());
        }

        if (restaurant.getName() != null) {
            restaurant.setName(updateRestaurantRequest.name());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new Exception("Restaurant with id " + restaurantId + " not found!"));
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found with owner id " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        List<RestaurantDto> favorites = new ArrayList<>(user.getFavorites());

        if (!user.getFavorites().isEmpty()) {
            for (RestaurantDto restaurantDto : user.getFavorites()) {
                if (restaurantDto.getId().equals(dto.getId())) {
                    favorites.removeIf(restaurantDto1 -> restaurantDto1.getId().equals(restaurantDto.getId()));
                } else {
                    favorites.add(dto);
                }
                user.setFavorites(favorites);
                userRepository.save(user);
            }
        } else {
            user.getFavorites().add(dto);
            userRepository.save(user);
        }

        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.getOpen());
        return restaurantRepository.save(restaurant);
    }
}


















