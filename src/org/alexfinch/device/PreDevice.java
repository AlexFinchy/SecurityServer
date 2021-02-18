package org.alexfinch.device;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

import org.alexfinch.DeviceType;
import org.alexfinch.Server;

public class PreDevice extends Thread{
	
	Socket socket;
	
	public PreDevice(Socket socket) {
		
		this.socket = socket;
		
	}
	
	@Override
	public void run() {
		
		
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			//Accept Socket
			//Motion Detector
			//Tablet
			
			//GET UUID FIRST
			Object FirstObject = in.readObject();
			if(!(FirstObject instanceof UUID)) {
				socket.close();
			}
			
			UUID uuid = (UUID) FirstObject;
			
			if(Server.OfflineDevices.containsKey(uuid)) {
				
				DeviceInfo deviceInfo = Server.OfflineDevices.get(uuid);
				
				if(deviceInfo.getDeviceType() == DeviceType.MotionDetector) {
					
					SensorDevice sensorDevice = new SensorDevice(socket, in, out, deviceInfo);
					
				} else if(deviceInfo.getDeviceType() == DeviceType.ControlPI) {
				
					ControlDevice controlDevice = new ControlDevice(socket, in, out, deviceInfo);
					
					
				}
				
				Server.OfflineDevices.remove(uuid);
				
			}
			//IF DEVICE NOT DEFINED
			
			//GET DEVICE TYPE AND MAKE A NEW TYPE OF THAT DEVICE
			
			DeviceInfo deviceInfo = new DeviceInfo();
			
			//GET SERVER TO ENTER THE DEVICE NAME
			
			
			
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
