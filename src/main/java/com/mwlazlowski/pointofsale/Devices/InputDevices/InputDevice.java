package com.mwlazlowski.pointofsale.Devices.InputDevices;

public interface InputDevice {
    void scan(String code);
    String getScannedCode();
}
