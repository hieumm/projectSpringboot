package com.example.projectb2c.service;



import com.example.projectb2c.entity.CartProduct;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    Map<Integer, CartProduct> maps = new HashMap<>();//gio hang

    @Override
    public void add(CartProduct item) {
        CartProduct cartProduct = maps.get(item.getProductId());
        if (cartProduct == null) {
            maps.put(item.getProductId(), item);
        } else {
            cartProduct.setQuantity(cartProduct.getQuantity() + 1);
        }
    }

    @Override
    public void remove(Integer id) {
        maps.remove(id);

    }

    @Override
    public void clear() {
        maps.clear();
    }

    @Override
    public Collection<CartProduct> getAll() {
        return maps.values();
    }

    @Override
    public double getAmount() {
        return maps.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }
}
