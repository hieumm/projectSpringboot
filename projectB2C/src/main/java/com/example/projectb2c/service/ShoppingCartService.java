package com.example.projectb2c.service;





import com.example.projectb2c.entity.CartProduct;

import java.util.Collection;

public interface ShoppingCartService {
    void add(CartProduct item);

    void remove(Integer id);

    void clear();

    public Collection<CartProduct> getAll();

    double getAmount();
}
