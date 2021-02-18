package org.alexfinch.user;

import java.io.IOException;
import java.util.HashMap;

import org.alexfinch.LoginResponse;
import org.alexfinch.device.Device;

public class UserDeviceMap extends HashMap<User, Device>{
	
	@Override
	public Device remove(Object key) {
		User user = (User) key;
		
		try {
			get(user).out.writeObject(LoginResponse.Logout);
			user.Disconnect();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		
		return super.remove(key);
	}
	

}
