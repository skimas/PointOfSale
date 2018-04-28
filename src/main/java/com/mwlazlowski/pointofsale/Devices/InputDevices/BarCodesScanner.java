package com.mwlazlowski.pointofsale.Devices.InputDevices;


public class BarCodesScanner implements InputDevice {

    String scannedBarCode;

    public String getScannedCode() {
        return scannedBarCode;
    }

    public void scan(String barCode) {
        this.scannedBarCode = barCode;
    }
}
