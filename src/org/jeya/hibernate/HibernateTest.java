package org.jeya.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jeya.dto.Address;
import org.jeya.dto.FourWheeler;
import org.jeya.dto.FourWheeler2;
import org.jeya.dto.FourWheeler3;
import org.jeya.dto.LoginName;
import org.jeya.dto.TwoWheeler;
import org.jeya.dto.TwoWheeler2;
import org.jeya.dto.TwoWheeler3;
import org.jeya.dto.UserDetails;
import org.jeya.dto.UserDetails10;
import org.jeya.dto.UserDetails2;
import org.jeya.dto.UserDetails3;
import org.jeya.dto.UserDetails4;
import org.jeya.dto.UserDetails5;
import org.jeya.dto.UserDetails6;
import org.jeya.dto.UserDetails7;
import org.jeya.dto.UserDetails8;
import org.jeya.dto.UserDetails9;
import org.jeya.dto.Vehicle;
import org.jeya.dto.Vehicle2;
import org.jeya.dto.Vehicle3;
import org.jeya.dto.Vehicle4;
import org.jeya.dto.Vehicle5;
import org.jeya.dto.Vehicle6;
import org.jeya.dto.Vehicle7;
import org.jeya.dto.Vehicle8;

public class HibernateTest {
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate_postgresql.cfg.xml")
				.buildSessionFactory();
		//saveDataObjectAsAttribute(sessionFactory);
		//retrieveSimpleData(sessionFactory);
		//objectAsPrimaryKey(sessionFactory);
		//oneToManyMappingCollection(sessionFactory);
		//lazyLoadingEagerLoading(sessionFactory);
		//oneToOneMapping(sessionFactory);
		//oneToManyMappingInSeparateTableByDefault(sessionFactory);
		//manyToOneMapping(sessionFactory);
		//oneToManyMappingAppendColumn(sessionFactory);
		//manyToManyMapping(sessionFactory);
		//cascadeType(sessionFactory);
		//handleInheritanceBySingleTableStrategy(sessionFactory);
		//handleInheritanceByTablePerClassStrategy(sessionFactory);
		handleInheritanceByJoinedStrategy(sessionFactory);
	}

	private static void handleInheritanceByJoinedStrategy(SessionFactory sessionFactory) {
		Vehicle8 vehicle = new Vehicle8();
		vehicle.setVehicleName("Car");
		
		TwoWheeler3 vehicle61 = new TwoWheeler3();
		vehicle61.setVehicleName("Bike");
		vehicle61.setSteeringHandle("Bike Steering Handle");
		
		FourWheeler3 vehicle62 = new FourWheeler3();
		vehicle62.setVehicleName("Porsche");
		vehicle62.setSteeringWheel("Porsche Steering Wheel");
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(vehicle);
		session.save(vehicle61);
		session.save(vehicle62);
		session.getTransaction().commit();
		session.close();
	}

	private static void handleInheritanceByTablePerClassStrategy(SessionFactory sessionFactory) {
		Vehicle7 vehicle = new Vehicle7();
		vehicle.setVehicleName("Car");
		
		TwoWheeler2 vehicle61 = new TwoWheeler2();
		vehicle61.setVehicleName("Bike");
		vehicle61.setSteeringHandle("Bike Steering Handle");
		
		FourWheeler2 vehicle62 = new FourWheeler2();
		vehicle62.setVehicleName("Porsche");
		vehicle62.setSteeringWheel("Porsche Steering Wheel");
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(vehicle);
		session.save(vehicle61);
		session.save(vehicle62);
		session.getTransaction().commit();
		session.close();
	}

	private static void handleInheritanceBySingleTableStrategy(SessionFactory sessionFactory) {
		Vehicle6 vehicle = new Vehicle6();
		vehicle.setVehicleName("Car");
		
		TwoWheeler vehicle61 = new TwoWheeler();
		vehicle61.setVehicleName("Bike");
		vehicle61.setSteeringHandle("Bike Steering Handle");
		
		FourWheeler vehicle62 = new FourWheeler();
		vehicle62.setVehicleName("Porsche");
		vehicle62.setSteeringWheel("Porsche Steering Wheel");
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(vehicle);
		session.save(vehicle61);
		session.save(vehicle62);
		session.getTransaction().commit();
		session.close();
	}

	private static void cascadeType(SessionFactory sessionFactory) {
		UserDetails10 userDetails10 = new UserDetails10();
		userDetails10.setUserName("User 10");
		
		Vehicle5 vehicle51 = new Vehicle5();
		vehicle51.setVehicleName("User 10 Car1");
		
		Vehicle5 vehicle52 = new Vehicle5();
		vehicle52.setVehicleName("User 10 Car2");
		
		userDetails10.getVehicle().add(vehicle51);
		userDetails10.getVehicle().add(vehicle52);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(userDetails10);
		session.getTransaction().commit();
		session.close();
	}

	private static void manyToManyMapping(SessionFactory sessionFactory) {
		UserDetails9 userDetails91 = new UserDetails9();
		userDetails91.setUserName("User 91");
		UserDetails9 userDetails92 = new UserDetails9();
		userDetails92.setUserName("User 92");
		
		Vehicle4 vehicle1 = new Vehicle4();
		vehicle1.setVehicleName("User 91 Car1");
		Vehicle4 vehicle2 = new Vehicle4();
		vehicle2.setVehicleName("User 91 Car2");
		
		userDetails92.getVehicle().add(vehicle2);
		vehicle2.getUserList().add(userDetails92);
		
		vehicle1.getUserList().add(userDetails91);
		userDetails91.getVehicle().add(vehicle1);
		
		
		vehicle2.getUserList().add(userDetails91);
		userDetails91.getVehicle().add(vehicle2);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails91);
		session.save(userDetails92);
		session.save(vehicle1);
		session.save(vehicle2);
		session.getTransaction().commit();
		session.close();
	}

	private static void oneToManyMappingAppendColumn(SessionFactory sessionFactory) {
		UserDetails8 userDetails81 = new UserDetails8();
		userDetails81.setUserName("User 81");
		
		Vehicle3 vehicle1 = new Vehicle3();
		vehicle1.setVehicleName("User 81 Car1");
		vehicle1.setUserDetails(userDetails81);
		userDetails81.getVehicle().add(vehicle1);
		
		Vehicle3 vehicle2 = new Vehicle3();
		vehicle2.setVehicleName("User 81 Car2");
		vehicle2.setUserDetails(userDetails81);
		userDetails81.getVehicle().add(vehicle2);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails81);
		session.save(vehicle1);
		session.save(vehicle2);
		session.getTransaction().commit();
		session.close();
	}

	private static void manyToOneMapping(SessionFactory sessionFactory) {
		UserDetails7 userDetails71 = new UserDetails7();
		userDetails71.setUserName("User 71");

		Vehicle2 vehicle1 = new Vehicle2();
		vehicle1.setVehicleName("User 71 Car1");
		vehicle1.setUserDetails(userDetails71);

		Vehicle2 vehicle2 = new Vehicle2();
		vehicle2.setVehicleName("User 71 Car2");
		vehicle2.setUserDetails(userDetails71);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails71);
		session.save(vehicle1);
		session.save(vehicle2);
		session.getTransaction().commit();
		session.close();
	}

	private static void oneToManyMappingInSeparateTableByDefault(SessionFactory sessionFactory) {
		UserDetails6 userDetails61 = new UserDetails6();
		userDetails61.setUserName("User 61");
		
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setVehicleName("User 61 Car1");
		userDetails61.getVehicle().add(vehicle1);
		
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("User 61 Car2");
		userDetails61.getVehicle().add(vehicle2);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails61);
		session.save(vehicle1);
		session.save(vehicle2);
		session.getTransaction().commit();
		session.close();
	}

	private static void oneToOneMapping(SessionFactory sessionFactory) {
		UserDetails5 userDetails51 = new UserDetails5();
		userDetails51.setUserName("User 51");
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("User 51 Car");
		userDetails51.setVehicle(vehicle);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails51);
		session.save(vehicle);
		session.getTransaction().commit();
		session.close();
	}

	private static void lazyLoadingEagerLoading(SessionFactory sessionFactory) {
		UserDetails4 userDetails41 = new UserDetails4();
		userDetails41.setUserName("User 41");
		Collection<Address> addressList = new ArrayList<>();
		addressList.add(new Address("Home Street41", "Home City41", "Home State41", "Home Pincode41"));
		addressList.add(new Address("Home Street42", "Home City42", "Home State42", "Home Pincode42"));

		userDetails41.setListOfAddresses(addressList);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails41);
		session.getTransaction().commit();
		session.close();
		
		userDetails41 = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		userDetails41 = session.get(UserDetails4.class, 6);
		// in default case or @ElementCollection(fetch=FetchType.LAZY)
		//session.close(); // Exception in thread "main" org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: 
		//org.jeya.dto.UserDetails4.listOfAddresses, could not initialize proxy - no Session
		System.out.println(userDetails41.getListOfAddresses().size());
		session.close();
		
		System.out.println(userDetails41.getListOfAddresses().size());
	}

	private static void oneToManyMappingCollection(SessionFactory sessionFactory) {
		UserDetails3 userDetails31 = new UserDetails3();
		userDetails31.setUserName("User 31");
		Collection<Address> listOfAddresses = new ArrayList<>();
		listOfAddresses.add(new Address("Home Street31", "Home City31", "Home State31", "Home Pincode31"));
		listOfAddresses.add(new Address("Home Street32", "Home City32", "Home State32", "Home Pincode32"));

		userDetails31.setListOfAddresses(listOfAddresses);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails31);
		session.getTransaction().commit();
		session.close();
	}

	private static void objectAsPrimaryKey(SessionFactory sessionFactory) {
		UserDetails2 userDetails21 = new UserDetails2();
		LoginName loginName = new LoginName();
		loginName.setFirstName("First name");
		loginName.setUserId(1);
		loginName.setSecurityNo("Security no1");
		userDetails21.setUserId(loginName);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails21);
		session.getTransaction().commit();
		session.close();
	}

	private static void retrieveSimpleData(SessionFactory sessionFactory) {
		UserDetails userDetails = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		userDetails = session.get(UserDetails.class, 1); // primary key
		session.close();
		System.out.println("User Name : " + userDetails.getUserName());// First user from getter (get value from db and bind to attribute, invoke getter method
		System.out.println("Description : " + userDetails.getDescription());
	}

	private static void saveDataObjectAsAttribute(SessionFactory sessionFactory) {
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

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails);
		session.save(userDetails2);
		session.getTransaction().commit();
		session.close();
	}
}