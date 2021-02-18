package org.alexfinch.device;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.sound.midi.Receiver;

import org.alexfinch.DeviceType;
import org.alexfinch.Server;
import org.alexfinch.user.UniqueKey;

public class Device extends Thread implements Serializable{
	
	public ObjectOutputStream out;	
	public ObjectInputStream in;
	public Socket socket;
	public LoginSystem CurrentLoginSystem;
	public DeviceInfo deviceInfo;
	public Device(Socket socket, ObjectInputStream in, ObjectOutputStream out,DeviceInfo deviceInfo) {
		this.socket = socket;
		this.out = out;
		try {
			this.out.flush();
			this.in = in;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	

	
}
