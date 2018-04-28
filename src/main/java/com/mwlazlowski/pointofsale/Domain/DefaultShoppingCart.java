package com.mwlazlowski.pointofsale.Domain;

import java.util.ArrayList;
import java.util.List;

public class DefaultShoppingCart implements ShoppingCart {

    private List<Product> listOfProducts;
    private double totalCost;

    public DefaultShoppingCart() {
        this.listOfProducts = new ArrayList<Product>();

        this.totalCost = 0.0;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void addProductToCart(Product product) {
        listOfProducts.add(product);
    }

    public double getTotalCost() {
        for(Product product: listOfProducts){
            this.totalCost+=product.getPrice();
        }
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
