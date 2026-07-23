package com.novacart.order.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    @Id private String id;
    private String userId;
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private double total;
    private LocalDateTime orderedAt;
    private String status;

    public Order() {}
    public Order(String userId, String productId, String productName, double price, int quantity) {
        this.userId = userId; this.productId = productId; this.productName = productName;
        this.price = price; this.quantity = quantity; this.total = price * quantity;
        this.orderedAt = LocalDateTime.now(); this.status = "placed";
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; this.total = price * quantity; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; this.total = price * quantity; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public LocalDateTime getOrderedAt() { return orderedAt; }
    public void setOrderedAt(LocalDateTime orderedAt) { this.orderedAt = orderedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
