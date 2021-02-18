package org.alexfinch.device;

import java.io.IOException;

import org.alexfinch.LoginResponse;
import org.alexfinch.Pin;
import org.alexfinch.Server;
import org.alexfinch.user.User;

public class LoginSystem {

	public User CurrentUser;
	
	
	
	Device device;
	public LoginSystem(Device device) {
		System.out.println("New Login System");
		this.device = device;
		Object object;
		try {
			while((object = device.in.readObject()) !=null) {
				System.out.println("Trying to read an object");
				if(object instanceof Pin) {
					Pin pin = (Pin) object;
					
					if(Server.UserInfoPins.containsKey(pin)) {
						
						//Write Login Code Later
						device.out.writeObject(LoginResponse.UserFound);
						device.out.writeObject(Server.UserInfoPins.get(pin));
						CurrentUser = new User(Server.UserInfoPins.get(pin),device);
						
						
						
					} else if(Server.OverRidePins.containsKey(pin)) {
						
						//Active Protcol
						device.out.writeObject(LoginResponse.ProtocolAccepted);
						
						
					} else {
						
						device.out.writeObject(LoginResponse.UserNotFound);
						
					}
					
				}
				
			}
		} catch (Exception e) {
			try {
				device.socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Server.CurrentDevices.remove(device);
		}
	}

	
}
