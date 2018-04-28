package com.mwlazlowski.pointofsale;

import com.mwlazlowski.pointofsale.Domain.Product;

public interface ProductsDatabase {
    int getId();
    Product findByBarCode(String barcode);
    Product findByName(String name);
    void addToDatabase(Product product);
}
