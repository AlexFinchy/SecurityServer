package org.alexfinch.user;

import java.io.IOException;
import java.net.SocketException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Iterator;
import java.util.logging.LoggingMXBean;

import org.alexfinch.AlarmResponse;
import org.alexfinch.LoginResponse;
import org.alexfinch.Server;
import org.alexfinch.device.Device;

public class User {

	UserInfo info;
	
	Device device;
	
	
	
	volatile Boolean LoggedIn = true;
	
	public User(UserInfo user, Device device) {
		
		info = user;
		
		System.out.println("User has Tried to log in");
		if(Server.UserDevices.containsKey(this)) {
			
			
			for(User Users : Server.UserDevices.keySet()) {
				if(Users.equals(this)) {
					System.out.println("Equal");
					Server.UserDevices.remove(Users);
				}
				
			}
			
		}
		
		Server.UserDevices.put(this,device);
		
		
		System.out.println(user.Name + " Logged In");
		
		
		
		Object object;
		try {
			while(LoggedIn && ( object = device.in.readObject()) != null ) {
				if(LoggedIn) {
					if(object instanceof LoginResponse) {
						LoginResponse Request = (LoginResponse) object;
						if(Request == LoginResponse.Logout) {
							Server.UserDevices.remove(this);
						}
					} else if(object instanceof AlarmResponse) {
						AlarmResponse alarmResponse = (AlarmResponse) object;
						if(alarmResponse == AlarmResponse.AlarmManually | alarmResponse == AlarmResponse.AlarmTriggered) {
							
						} else if(alarmResponse == AlarmResponse.AlarmStopped) {
							
						}
						
						
					}
				}
			}
			System.out.println("Loop broken");
			
		} catch (ClassNotFoundException | IOException e) {
			
			
			
			//When Connection is over.
			System.out.println("The Device Disconected");
			Server.UserDevices.remove(this);
			Server.CurrentDevices.remove(device);
			try {
				device.socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return info.Name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof User) {
			User user = (User) obj;
			if(user.info.equals(info)) {
				return true;
			}
		}
		return false;
	}
	
	public void Disconnect() {
		LoggedIn = false;
			
		
	}
	
}
