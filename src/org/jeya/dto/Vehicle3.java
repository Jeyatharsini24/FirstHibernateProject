package org.jeya.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Vehicle3")
public class Vehicle3 {
	@Id
	@GeneratedValue
	private int vehicleId;
	private String vehicleName;
	@ManyToOne
	@JoinColumn(name="USERID")
	private UserDetails8 userDetails;

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public UserDetails8 getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails8 userDetails) {
		this.userDetails = userDetails;
	}
}