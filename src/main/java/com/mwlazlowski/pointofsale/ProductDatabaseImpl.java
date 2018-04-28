package com.mwlazlowski.pointofsale;

import com.mwlazlowski.pointofsale.Domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductDatabaseImpl implements ProductsDatabase {

    private int id;
    private Map<String, Product> database;

    public ProductDatabaseImpl() {
        this.database = new HashMap<String, Product>();
        id=0;
    }


    public int getId() {
        return id;
    }

    public Product findByBarCode(String barcode) {
        Product product = database.get(barcode);
        if(product!=null){
            return product;
        }
        return null;
    }

    public Product findByName(String name) {
        return null;
    }

    public void addToDatabase(Product product) {
        if(database.get(product.getBarCode())==null ) {
            database.put(product.getBarCode(), product);
            id+=1;
        } else {
            System.out.println("The product can not be added, product available in database");
        }
    }
}
