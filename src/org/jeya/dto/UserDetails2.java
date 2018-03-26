package org.jeya.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS2")
public class UserDetails2 {
	@EmbeddedId
	private LoginName userId; // primary key

	public LoginName getUserId() {
		return userId;
	}

	public void setUserId(LoginName userId) {
		this.userId = userId;
	}
}