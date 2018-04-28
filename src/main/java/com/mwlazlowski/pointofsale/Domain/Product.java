package com.mwlazlowski.pointofsale.Domain;

public interface Product {
    void setId(int id);
    String getName();
    String getBarCode();
    double getPrice();
}
