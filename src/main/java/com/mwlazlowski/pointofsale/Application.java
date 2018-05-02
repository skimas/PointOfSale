package com.mwlazlowski.pointofsale;

import com.mwlazlowski.pointofsale.Domain.Product;

public class Application {

    public static void main(String[] args){

        //Example of using
        PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.setupDatabase();

        pointOfSale.addProductToCart("123456780");
        pointOfSale.addProductToCart("123456781");
        //Product not found
        pointOfSale.addProductToCart("123456784");
        //Invalid bar-code
        pointOfSale.addProductToCart("");
        pointOfSale.addProductToCart("exit");
    }
}
