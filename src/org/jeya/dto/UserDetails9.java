package org.jeya.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;@Entity
@Table(name = "USER_DETAILS9")
public class UserDetails9 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	private String userName;
	@ManyToMany
	@JoinTable(name="USER9S_VEHICLES",
	joinColumns=@JoinColumn(name="USER_ID"))
	private Collection<Vehicle4> vehicle = new ArrayList<>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Collection<Vehicle4> getVehicle() {
		return vehicle;
	}

	public void setVehicle(Collection<Vehicle4> vehicle) {
		this.vehicle = vehicle;
	}
}