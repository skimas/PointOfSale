package com.mwlazlowski.pointofsale;

import com.mwlazlowski.pointofsale.Domain.Product;

public class Application {

    public static void main(String[] args){

        PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.setupDatabase();

        Product p1 = pointOfSale.scanProductAndValidateOnLCD("123456780");
        pointOfSale.addProductToCart(p1);
        Product p2 = pointOfSale.scanProductAndValidateOnLCD("123456781");
        pointOfSale.addProductToCart(p2);
        //Product not found
        Product p3 = pointOfSale.scanProductAndValidateOnLCD("123456784");
        pointOfSale.addProductToCart(p3);
        //Invalid bar-code
        Product p4 = pointOfSale.scanProductAndValidateOnLCD("");
        pointOfSale.addProductToCart(p4);
        pointOfSale.scanProductAndValidateOnLCD("exit");
    }
}
