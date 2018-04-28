package com.mwlazlowski.pointofsale.Devices.OutputDevices;

public interface OutputDevice {
    void print(String details);
    int getMaxTextLength();
}
