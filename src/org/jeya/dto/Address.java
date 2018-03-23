package org.jeya.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	@Column(name="COLUMN_STREET")
	private String street;
	@Column(name="COLUMN_CITY")
	private String city;
	@Column(name="COLUMN_STATE")
	private String state;
	@Column(name="COLUMN_PINCODE")
	private String pincode;
	
	// there should be default constructor: otherwise org.hibernate.InstantiationException: No default constructor for entity:  : org.jeya.dto.Address
	// will be thrown
	public Address()
	{
		
	}
	
	public Address(String street, String city, String state, String pincode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}