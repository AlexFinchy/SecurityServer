package org.alexfinch.user;

import java.io.Serializable;

public class UniqueKey implements Serializable{

	
	
	
	/**
	 * 
	 */
	public final float Key = 343242324234234L;
	
	private static final long serialVersionUID = 321111967490227784L;
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof UniqueKey) {
			
			UniqueKey key = (UniqueKey) obj;
			if(key.Key == Key) {
				return true;
			}
			
		}
		return false;
	}
	

}
