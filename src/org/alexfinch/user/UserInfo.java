package org.alexfinch.user;

import java.io.Serializable;

public class UserInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1544020124340229069L;
	String Name;
	String Pin;
	int Clearance;
	Boolean MasterUser;
	public UserInfo(String Name, String Pin, int Clearance, Boolean MasterUser) {
		this.Name = Name;
		this.Pin = Pin;
		this.Clearance = Clearance;
		this.MasterUser = MasterUser;
	}
	
	@Override
	public int hashCode() {
		return Name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UserInfo) {
			UserInfo userInfo = (UserInfo) obj;
			return userInfo.Name.equals(Name);
			
		}
		return false;
	}

}
