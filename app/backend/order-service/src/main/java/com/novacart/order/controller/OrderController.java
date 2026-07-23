package com.novacart.order.controller;

import com.novacart.order.dto.ApiResponse;
import com.novacart.order.dto.OrderRequest;
import com.novacart.order.entity.Order;
import com.novacart.order.service.OrderService;
import com.novacart.order.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;
    private final JwtUtil jwtUtil;
    public OrderController(OrderService orderService, JwtUtil jwtUtil) { this.orderService = orderService; this.jwtUtil = jwtUtil; }

    @GetMapping({"", "/"})
    public ResponseEntity<ApiResponse<List<Order>>> getUserOrders(HttpServletRequest request) {
        String userId = getUserId(request);
        if (userId == null) return ResponseEntity.badRequest().body(ApiResponse.error("Valid authentication is required"));
        return ResponseEntity.ok(ApiResponse.success(orderService.getUserOrders(userId)));
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ApiResponse<Order>> createOrder(@Valid @RequestBody OrderRequest request, HttpServletRequest httpRequest) {
        String userId = getUserId(httpRequest);
        if (userId == null) return ResponseEntity.badRequest().body(ApiResponse.error("Valid authentication is required"));
        return ResponseEntity.ok(ApiResponse.success(orderService.createOrder(userId, request)));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() { return ResponseEntity.ok(ApiResponse.success("Order service is healthy")); }

    private String getUserId(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) return null;
        String token = bearerToken.substring(7);
        String email = jwtUtil.extractEmail(token);
        return jwtUtil.validateToken(token, email) ? jwtUtil.extractUserId(token) : null;
    }
}
