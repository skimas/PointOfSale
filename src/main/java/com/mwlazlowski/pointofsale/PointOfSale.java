package com.mwlazlowski.pointofsale;

import com.mwlazlowski.pointofsale.Devices.InputDevices.BarCodesScanner;
import com.mwlazlowski.pointofsale.Devices.InputDevices.InputDevice;
import com.mwlazlowski.pointofsale.Devices.OutputDevices.LCDDisplay;
import com.mwlazlowski.pointofsale.Devices.OutputDevices.OutputDevice;
import com.mwlazlowski.pointofsale.Devices.OutputDevices.Printer;
import com.mwlazlowski.pointofsale.Domain.DefaultProduct;
import com.mwlazlowski.pointofsale.Domain.DefaultShoppingCart;
import com.mwlazlowski.pointofsale.Domain.Product;
import com.mwlazlowski.pointofsale.Domain.ShoppingCart;

import java.text.DecimalFormat;

public class PointOfSale {

    private InputDevice barCodesScanner;
    private OutputDevice lcdDisplay;
    private OutputDevice printer;
    private ProductsDatabase productsDatabase;
    private ShoppingCart shoppingCart;

    public PointOfSale() {
        barCodesScanner = new BarCodesScanner();
        lcdDisplay = new LCDDisplay();
        printer = new Printer();
        productsDatabase = new ProductDatabaseImpl();
        shoppingCart = new DefaultShoppingCart();
    }

    public PointOfSale(ProductsDatabase productsDatabase) {
        barCodesScanner = new BarCodesScanner();
        lcdDisplay = new LCDDisplay();
        printer = new Printer();
        this.productsDatabase = productsDatabase;
        shoppingCart = new DefaultShoppingCart();
    }

    public PointOfSale(InputDevice barCodesScanner, OutputDevice lcdDisplay, OutputDevice printer, ProductsDatabase productsDatabase, ShoppingCart shoppingCart) {
        this.barCodesScanner = barCodesScanner;
        this.lcdDisplay = lcdDisplay;
        this.printer = printer;
        this.productsDatabase = productsDatabase;
        this.shoppingCart = shoppingCart;
    }

    public void setupDatabase(){
        Product butter = new DefaultProduct(productsDatabase.getId(), "Butter", "123456780", 3.75);
        Product ham = new DefaultProduct(productsDatabase.getId(), "Ham", "123456781", 6.25);
        Product bred = new DefaultProduct(productsDatabase.getId(), "Bred", "123456782", 1.11);
        productsDatabase.addToDatabase(butter);
        productsDatabase.addToDatabase(ham);
        productsDatabase.addToDatabase(bred);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public InputDevice getBarCodesScanner() {
        return barCodesScanner;
    }

    public void setBarCodesScanner(InputDevice barCodesScanner) {
        this.barCodesScanner = barCodesScanner;
    }

    public OutputDevice getLcdDisplay() {
        return lcdDisplay;
    }

    public void setLcdDisplay(OutputDevice lcdDisplay) {
        this.lcdDisplay = lcdDisplay;
    }

    public OutputDevice getPrinter() {
        return printer;
    }

    public void setPrinter(OutputDevice printer) {
        this.printer = printer;
    }

    public ProductsDatabase getProductsDatabase() {
        return productsDatabase;
    }

    public void setProductsDatabase(ProductsDatabase productsDatabase) {
        this.productsDatabase = productsDatabase;
    }

    public Product scanProductAndValidateOnLCD(String barCode){
        barCodesScanner.scan(barCode);
        Product product = productsDatabase.findByBarCode(barCodesScanner.getScannedCode());
        if(barCode.equals("exit")){
            printReceipt();
        } else if(barCode.equals("")){
            lcdDisplay.print("Invalid bar-code");
        } else if(product==null){
            lcdDisplay.print("Product not found");
        } else {
            lcdDisplay.print(product.getName() + "\t\t" + product.getPrice() + "\n");
        }
        return product;
    }

    public void addProductToCart(Product product){
        if(product!=null) shoppingCart.addProductToCart(product);
    }

    public void printReceipt(){
        StringBuilder receipt = new StringBuilder();
        String tmp;
        int length;
        for (Product product: shoppingCart.getListOfProducts()){
            tmp=product.getName() + product.getPrice();
            length = printer.getMaxTextLength() - tmp.length();
            receipt.append(product.getName());
            for (int i = 0; i<length; ++i){
                receipt.append(" ");
            }
            receipt.append(product.getPrice())
                    .append("\n");
        }
        receipt.append("Total:");
        tmp=String.format("%.2f", shoppingCart.getTotalCost()) + "total:";
        length=printer.getMaxTextLength()-tmp.length();
        for (int i = 0; i<length; ++i){
            receipt.append(" ");
        }
        receipt.append(String.format("%.2f", shoppingCart.getTotalCost()));
        printer.print(receipt.toString());
    }
}
