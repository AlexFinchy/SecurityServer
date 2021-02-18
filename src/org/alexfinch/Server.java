package org.alexfinch;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.ResourceBundle.Control;

import org.alexfinch.device.ControlDevice;
import org.alexfinch.device.Device;
import org.alexfinch.device.DeviceInfo;
import org.alexfinch.device.PreDevice;
import org.alexfinch.device.SensorDevice;
import org.alexfinch.user.UserDeviceMap;
import org.alexfinch.user.UserInfo;

public class Server {
	
	
	public static HashMap<UUID,DeviceInfo> OfflineDevices = new HashMap<>();

	public static UserDeviceMap UserDevices = new UserDeviceMap();
	
	public static ArrayList<Device> CurrentDevices = new ArrayList<>();
	
	public static HashMap<Pin,UserInfo> UserInfoPins = new HashMap<>();
	
	public static HashMap<Pin,Protocol> OverRidePins = new HashMap<>();
	
	volatile static Boolean AlarmTrigged = false;
	
	public static void main(String[] args) {
		
		//Manual Adding for Name CBA database no point
		
		

				
				try {
					
					ManualInput manualInput = new ManualInput();
					manualInput.start();
					
					Timer t = new Timer();
					t.schedule(new TimerTask() {
						
						@Override
						public void run() {
							//Alarm sending
							
							if(AlarmTrigged) {
								WriteToAll(AlarmResponse.AlarmTriggered);
							} else {
								WriteToAll(AlarmResponse.AlarmCancel);
							}
							
							
						}
					}, 1000,1000);
					
					ServerSocket serverSocket = new ServerSocket(1353);
					while(!serverSocket.isClosed()) {
					
						//ISSUE IS the more time this takes the worse connection time so it might be worth creating a preDevice
						
						Socket socket = serverSocket.accept();
						
						new PreDevice(socket);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

	public static void WriteToAll(AlarmResponse alarmResponse) {
		
		for(Device device : CurrentDevices) {
			try {
				if(device instanceof ControlDevice) {
					device.out.flush();
					device.out.writeObject(alarmResponse);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
		
	
	
}
