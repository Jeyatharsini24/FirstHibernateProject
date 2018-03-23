package org.jeya.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TABLE_USER_DETAILS2_BY_TABLE")
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