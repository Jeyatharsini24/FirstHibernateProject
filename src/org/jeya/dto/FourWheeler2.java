package org.jeya.dto;

import javax.persistence.Entity;

@Entity
public class FourWheeler2 extends Vehicle7 {
	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
}