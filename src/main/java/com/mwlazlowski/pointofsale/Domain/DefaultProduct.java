package com.mwlazlowski.pointofsale.Domain;

public class DefaultProduct implements Product {

    private int id;
    private String name;
    private String barCode;
    private double price;

    public DefaultProduct(int id, String name, String barCode, Double price){
        this.id = id;
        this.name = name;
        this.barCode = barCode;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id =id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return this.barCode;
    }
}
