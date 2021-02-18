package org.alexfinch.device;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.alexfinch.Server;
import org.alexfinch.user.UniqueKey;

public class ControlDevice extends Device {

	
	
	public ControlDevice(Socket socket, ObjectInputStream in, ObjectOutputStream out, DeviceInfo deviceInfo) {
		super(socket, in, out, deviceInfo);
		TabletSetup();
		// TODO Auto-generated constructor stub
	}

	public void TabletSetup() {
		try {

			
			Object HandshakeObject = in.readObject();
			if(HandshakeObject instanceof UniqueKey) {
				UniqueKey uniqueKey = (UniqueKey) HandshakeObject;
				if(uniqueKey.equals(new UniqueKey())) {
					System.out.println("Valid Device was Connected");
					Server.CurrentDevices.add(this);
					CurrentLoginSystem = new LoginSystem(this); //Fix this
				} else {
					socket.close();
				}

				
			}
			
			
		} catch (IOException e) {
			
			Server.CurrentDevices.remove(this);
			System.out.println("Someone Logged out badly");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
