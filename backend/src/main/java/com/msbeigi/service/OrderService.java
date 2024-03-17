package com.msbeigi.service;

import com.msbeigi.model.Order;
import com.msbeigi.model.User;
import com.msbeigi.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest request, User user) throws Exception;
    Order updateOrder(Long orderId, String orderStatus) throws Exception;
    void cancelOrder(Long orderId) throws Exception;
    List<Order> getUsersOrder(Long userId) throws Exception;
    List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;
    Order findOrderById(Long orderId) throws Exception;
}
