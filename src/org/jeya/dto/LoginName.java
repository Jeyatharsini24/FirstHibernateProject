package org.jeya.dto;

import java.io.Serializable;

public class LoginName implements Serializable{
	private int userId;
	private String firstName;
	private String securityNo;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecurityNo() {
		return securityNo;
	}

	public void setSecurityNo(String securityNo) {
		this.securityNo = securityNo;
	}
}