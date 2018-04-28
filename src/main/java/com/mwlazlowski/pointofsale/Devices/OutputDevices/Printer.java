package com.mwlazlowski.pointofsale.Devices.OutputDevices;

public class Printer implements OutputDevice {

    private static int defaultMaxTextLength =30;
    private int maxTextLength;

    public Printer() {
        this.maxTextLength = defaultMaxTextLength;
    }

    public Printer(int maxTextLength) {
        this.maxTextLength = maxTextLength;
    }


    public int getMaxTextLength() {
        return maxTextLength;
    }

    public void setMaxTextLength(int maxTextLength) {
        this.maxTextLength = maxTextLength;
    }

    public void print(String details) {
        System.out.println("Printer:\n" + details);
    }
}
