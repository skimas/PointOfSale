package com.mwlazlowski.pointofsale.Devices.OutputDevices;

public class LCDDisplay implements OutputDevice{

    private static int defaultMaxTextLength =100;
    private int maxTextLength;

    public LCDDisplay() {
        this.maxTextLength=defaultMaxTextLength;
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
        return 0;
    }
}
