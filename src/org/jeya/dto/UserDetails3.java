package org.jeya.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "USER_DETAILS3")
public class UserDetails3 {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@ElementCollection
	@JoinTable(name="USER_ADDRESS",
			joinColumns=@JoinColumn(name="USER_ID"))
	@GenericGenerator(name="sequence-gen", strategy="sequence")
	@CollectionId(columns = { @Column(name="ADDRESS_ID") }, generator = "sequence-gen", type = @Type(type = "long"))
	private Collection<Address> listOfAddresses = new ArrayList<>();
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