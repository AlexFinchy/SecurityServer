package org.alexfinch.device;

import org.alexfinch.DeviceType;

public class DeviceInfo {
	
	java.util.UUID UUID;
	String DeviceName;
	DeviceType deviceType;

	public String getDeviceName() {
		return DeviceName;
	}
	
	public DeviceType getDeviceType() {
		return deviceType;
	}
	
}
