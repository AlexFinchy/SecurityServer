package org.alexfinch;

public class Deviceinfo {
	
	String UUID;
	
	String DeviceName;
	
	String DeviceLocation;
	
	DeviceType DeviceType;
	
	public Deviceinfo(String DeviceLocation, String DeviceName, String UUID, DeviceType DeviceType) {
		this.UUID = UUID;
		this.DeviceName = DeviceName;
		this.DeviceLocation = DeviceLocation;
		this.DeviceType = DeviceType;
	}
	
	

}

