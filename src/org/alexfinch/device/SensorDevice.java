package org.alexfinch.device;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SensorDevice extends Device {

	public SensorDevice(Socket socket, ObjectInputStream in, ObjectOutputStream out, DeviceInfo deviceInfo) {
		super(socket, in, out, deviceInfo);
		// TODO Auto-generated constructor stub
	}




}
