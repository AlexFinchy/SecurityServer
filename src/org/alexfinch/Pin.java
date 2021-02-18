package org.alexfinch;

import java.io.Serializable;

public class Pin implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7350090130871931838L;
	String pin;

	public Pin(String pin) {
		this.pin = pin;
	}
	
	@Override
	public int hashCode() {
		return this.pin.hashCode();
	}
	
	long hashcode() {
		return pin.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Pin) {
			Pin pin = (Pin) obj;
			if(pin.pin.equals(this.pin)) {
				return true;
			}
			
		}
		
		return false;
	}
	
}
