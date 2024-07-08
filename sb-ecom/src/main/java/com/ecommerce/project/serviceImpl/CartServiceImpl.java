package com.ecommerce.project.serviceImpl;

import com.ecommerce.project.payload.CartDTO;
import com.ecommerce.project.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Override
    public CartDTO addProductToCart(Long productId, Integer quantity) {
        return null;
    }
}
