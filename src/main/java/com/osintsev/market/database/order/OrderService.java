package com.osintsev.market.database.order;

import com.osintsev.market.rest.dto.Order;
import com.osintsev.market.rest.dto.User;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    List<Order> getOrderList(User user);
    Order getOrder(Long id);
    List<Order> getOrderList();
    void deleteOrder(Long id);
    void changeStatus(Long id, OrderStatus orderStatus);
}
