package com.novacart.order.service;

import com.novacart.order.dto.OrderRequest;
import com.novacart.order.entity.Order;
import com.novacart.order.repository.OrderRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) { this.orderRepository = orderRepository; }
    public List<Order> getUserOrders(String userId) { return orderRepository.findByUserId(userId); }
    public Order createOrder(String userId, OrderRequest request) {
        return orderRepository.save(new Order(userId, request.productId(), request.productName(), request.price(), request.quantity()));
    }
}
