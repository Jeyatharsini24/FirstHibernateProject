package org.jeya.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS4")
public class UserDetails4 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="USER4_ADDRESS",
			joinColumns=@JoinColumn(name="USER_ID"))
	private Collection<Address> listOfAddresses = new ArrayList<>(); //made this as public for lazy loading vs eager loading demonstration purpose only
	/**
	 * Earlier it was a hash set. Changed to array list because index supporting collection is needed
	 * to have primary key column for join table
	 */

	private String userName;

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

	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}

	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}
}