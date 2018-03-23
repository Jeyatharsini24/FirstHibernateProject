package org.jeya.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jeya.dto.Address;
import org.jeya.dto.LoginName;
import org.jeya.dto.UserDetails;
import org.jeya.dto.UserDetails2;
import org.jeya.dto.UserDetails3;

public class HibernateTest {
	public static void main(String[] args) {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(1);
		userDetails.setUserName("First user");
		userDetails.setSAddress("First user's address");
		userDetails.setHomeAddress(new Address("Home Street1", "Home City1", "Home State1", "Home Pincode1"));
		userDetails.setOfficeAddress(new Address("Office Street1", "Office City1", "Office State1", "Office Pincode1"));
		userDetails.setJoinedDate(new Date());
		userDetails.setDescription("Description of the user goes here");
		
		UserDetails userDetails2 = new UserDetails();
		userDetails2.setUserName("Second user");
		userDetails2.setSAddress("Second user's address");
		userDetails2.setHomeAddress(new Address("Home Street2", "Home City2", "Home State2", "Home Pincode2"));
		userDetails2.setOfficeAddress(new Address("Office Street2", "Office City2", "Office State2", "Office Pincode2"));
		userDetails2.setJoinedDate(new Date());
		userDetails2.setDescription("Description of the user2 goes here");

		SessionFactory sessionFactory = new Configuration().configure("hibernate_postgresql.cfg.xml")
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails);
		session.save(userDetails2);
		session.getTransaction().commit();
		session.close();
		
		userDetails = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		userDetails = session.get(UserDetails.class, 1); // primary key
		session.getTransaction().commit();
		session.close();
		System.out.println("User Name : " + userDetails.getUserName());// First user from getter (get value from db and bind to attribute, invoke getter method
		System.out.println("Description : " + userDetails.getDescription());
		
		UserDetails2 userDetails21 = new UserDetails2();
		LoginName loginName = new LoginName();
		loginName.setFirstName("First name");
		loginName.setUserId(1);
		loginName.setSecurityNo("Security no1");
		userDetails21.setUserId(loginName);
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails21);
		session.getTransaction().commit();
		session.close();
		
		UserDetails3 userDetails31 = new UserDetails3();
		userDetails31.setUserName("User 31");
		Set<Address> addressList = new HashSet();
		addressList.add(new Address("Home Street31", "Home City31", "Home State31", "Home Pincode31"));
		addressList.add(new Address("Home Street32", "Home City32", "Home State32", "Home Pincode32"));

		userDetails31.setListOfAddresses(addressList);
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails31);
		session.getTransaction().commit();
		session.close();
	}
}