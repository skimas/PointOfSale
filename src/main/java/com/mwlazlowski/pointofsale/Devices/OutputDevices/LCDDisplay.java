package com.mwlazlowski.pointofsale.Devices.OutputDevices;

public class LCDDisplay implements OutputDevice{

    private int maxTextLength;

    public LCDDisplay() {
        this.maxTextLength = 100;
    }

    public LCDDisplay(int maxTextLength) {
        this.maxTextLength = maxTextLength;
    }

    public void print(String details) {
        System.out.println("LCDDisplay:\n" + details);
    }

    public void setMaxTextLength(int maxTextLength) {
        this.maxTextLength = maxTextLength;
    }

    public int getMaxTextLength() {
        return maxTextLength;
    }
}
