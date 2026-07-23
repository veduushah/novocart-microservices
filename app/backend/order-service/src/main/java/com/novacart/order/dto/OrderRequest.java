package com.novacart.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
    @NotBlank(message = "Product ID is required") String productId,
    @NotBlank(message = "Product name is required") String productName,
    @NotNull(message = "Price is required") @Positive(message = "Price must be positive") Double price,
    @NotNull(message = "Quantity is required") @Positive(message = "Quantity must be positive") Integer quantity
) {}
