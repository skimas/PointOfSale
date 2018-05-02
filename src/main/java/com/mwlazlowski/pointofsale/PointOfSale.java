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
import java.util.List;

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

    public void addProductToCart(String barCode){
        Product product = scanProductAndValidateOnLCD(barCode);
        if(product!=null){
            shoppingCart.addProductToCart(product);
        }
    }
    //function for tests or if confirmation is needed
    public boolean addProduct(String barCode){
        Product product = scanProductAndValidateOnLCD(barCode);
        if(product!=null){
            shoppingCart.addProductToCart(product);
            return true;
        } else return false;
    }

    public Product scanProductAndValidateOnLCD(String barCode){
        barCodesScanner.scan(barCode);
        Product product = productsDatabase.findByBarCode(barCodesScanner.getScannedCode());
        if(barCode.equals("exit")){
            printReceipt();
        } else if(barCode.equals("")){
            lcdDisplay.print("Invalid bar-code\n");
        } else if(product==null){
            lcdDisplay.print("Product not found\n");
        } else {
            lcdDisplay.print(product.getName() + " " + product.getPrice() + "\n");
        }
        return product;
    }

    public void printReceipt(){
        StringBuilder receipt = new StringBuilder();
        String tmp;
        List<Product> listOfProducts = shoppingCart.getListOfProducts();
        for (Product product: listOfProducts){
            tmp=product.getName() + product.getPrice();
            receipt.append(product.getName())
                    .append(prepareSpaces(printer.getMaxTextLength() - tmp.length()))
                    .append(product.getPrice())
                    .append("\n");
        }
        double totalCost = shoppingCart.getTotalCost();
        tmp=String.format("%.2f", totalCost) + "total:";
        receipt.append("Total:")
                .append(prepareSpaces(printer.getMaxTextLength()-tmp.length()))
                .append(String.format("%.2f", totalCost));
        printer.print(receipt.toString());
    }

    private String prepareSpaces(int length){
        StringBuilder preparedSpaces = new StringBuilder();
        for (int i = 0; i<length; ++i){
            preparedSpaces.append(" ");
        }
        return preparedSpaces.toString();
    }
}
