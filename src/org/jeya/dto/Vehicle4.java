package org.jeya.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE4")
public class Vehicle4 {
	@Id
	@GeneratedValue
	private int vehicleId;
	private String vehicleName;
	@ManyToMany(mappedBy="vehicle")
	private Collection<UserDetails9> userList = new ArrayList<>();

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

	public Collection<UserDetails9> getUserList() {
		return userList;
	}

	public void setUserList(Collection<UserDetails9> userList) {
		this.userList = userList;
	}
}