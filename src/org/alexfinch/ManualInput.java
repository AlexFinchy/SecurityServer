package org.alexfinch;

import java.util.Scanner;

public class ManualInput extends Thread {

	@Override
	public void run() {
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()) {
			String Command = scanner.nextLine();
			System.out.println(Command);
			if(Command.equalsIgnoreCase("Alarm on")) {
				Server.AlarmTrigged = true;
			} else if(Command.equalsIgnoreCase("Alarm off")) {
				Server.AlarmTrigged = false;
				
				
			}
			
		}
		
	}
	
}
