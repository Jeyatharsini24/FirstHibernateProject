package org.jeya.dto;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "TABLE_USER_DETAILS_BY_ENTITY")
// or
@Table(name = "TABLE_USER_DETAILS_BY_TABLE")
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COLUMN_USER_ID")
	private int userId;

	@Column(name = "COLUMN_USER_NAME")
	@Basic
	private String userName;

	@Temporal(value = TemporalType.DATE)
	private Date joinedDate;

	@Embedded
	@AttributeOverrides({
	@AttributeOverride(name = "street", column = @Column(name="COLUMN_HOME_STREET")),
	@AttributeOverride(name = "city", column = @Column(name="COLUMN_HOME_CITY")),
	@AttributeOverride(name = "state", column = @Column(name="COLUMN_HOME_STATE")),
	@AttributeOverride(name = "pincode", column = @Column(name="COLUMN_HOME_PINCODE"))
	})
	private Address homeAddress;
	
	@Embedded
	private Address officeAddress;

	@Transient
	private String sAddress;
	@Lob
	private String description;

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

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getSAddress() {
		return sAddress;
	}

	public void setSAddress(String address) {
		this.sAddress = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHomeAddress(Address address) {
		this.homeAddress = address;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}
	
	public void setOfficeAddress(Address address) {
		this.officeAddress = address;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}
}