package com.mwlazlowski.pointofsale.Domain;

import java.util.List;

public interface ShoppingCart {
    double getTotalCost();
    List<Product> getListOfProducts();
    void addProductToCart(Product product);
}
