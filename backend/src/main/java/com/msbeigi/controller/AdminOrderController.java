package com.msbeigi.controller;

import com.msbeigi.model.Order;
import com.msbeigi.model.User;
import com.msbeigi.service.OrderService;
import com.msbeigi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable("id") Long restaurantId,
            @RequestParam(name = "order_status", required = false) String orderStatus,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getRestaurantsOrder(restaurantId, orderStatus);
        return ResponseEntity.ok(orders);
    }


    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable("orderStatus") String orderStatus,
            @PathVariable("id") Long orderId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.updateOrder(orderId, orderStatus);
        return ResponseEntity.ok(order);
    }
}
