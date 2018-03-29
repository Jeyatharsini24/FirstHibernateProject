package org.jeya.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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
import org.jeya.dto.UserDetails11;
import org.jeya.dto.UserDetails12;
import org.jeya.dto.UserDetails13;
import org.jeya.dto.UserDetails14;
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
		//handleInheritanceByJoinedStrategy(sessionFactory);
		//handleCRUDOperations(sessionFactory);
		//updateAfterSave(sessionFactory);
		//updateAfterRetrive(sessionFactory);
		//detachToPersistent(sessionFactory);
		//hQLAndQueryObject(sessionFactory);
		//pagination(sessionFactory);
		//sqlInjection(sessionFactory);
		//sqlInjectionSolutionByPositionalPlaceHolderSubstitution(sessionFactory);
		//sqlInjectionSolutionByNamePlaceHolderSubstitution(sessionFactory);
		//namedQuery(sessionFactory);
		//namedNativeQuery(sessionFactory);
		//criteriaAPI(sessionFactory);
		//criteriaAPIOR(sessionFactory);
		//criteriaAPIProjection(sessionFactory);
		//firstLevelCachingInAction(sessionFactory);
		//firstLevelCachingNotWorking(sessionFactory);
		//secondLevelCachingInAction(sessionFactory);
		queryCache(sessionFactory);
	}
	
	private static void queryCache(SessionFactory sessionFactory) {
		// use it with care because there might not be same query frequently without dirty
		// need to set both queries as cacheable
		create14(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from UserDetails14 where userId = 1");
		query.setCacheable(true); // make this query only cacheable
		List<UserDetails14> userDetails = (List<UserDetails14>) query.list();
		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();

		Query query2 = session.createQuery("from UserDetails14 where userId = 1");
		query2.setCacheable(true); 
		// set cacheable will hit database if there is no result already available
		// if not mark it as cacheable, it will hit database without looking at cache
		userDetails = (List<UserDetails14>) query2.list();
		session.getTransaction().commit();
		session.close();
	}

	private static void secondLevelCachingInAction(SessionFactory sessionFactory) {
		// there will be one select even after there is a session close
		// it's able to keep the cache across session using Ehcache
		create13(sessionFactory);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		UserDetails13 user = session.get(UserDetails13.class, 6);

		session.getTransaction().commit();
		session.close();
		System.out.println("User id : " + user.getUserId() + ", user name : " + user.getUserName());

		session = sessionFactory.openSession();
		session.beginTransaction();

		UserDetails13 user2 = session.get(UserDetails13.class, 6);
		session.getTransaction().commit();
		session.close();
		System.out.println("User id : " + user.getUserId() + ", user name : " + user.getUserName());
	}

	private static void firstLevelCachingNotWorking(SessionFactory sessionFactory) {
		// there will be two select query since session is closed
		// and a new session has been opened
		create(sessionFactory);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		UserDetails11 user = session.get(UserDetails11.class, 6);

		session.getTransaction().commit();
		session.close();
		System.out.println("User id : " + user.getUserId() + ", user name : " + user.getUserName());

		session = sessionFactory.openSession();
		session.beginTransaction();

		UserDetails11 user2 = session.get(UserDetails11.class, 6);
		session.getTransaction().commit();
		session.close();
		System.out.println("User id : " + user.getUserId() + ", user name : " + user.getUserName());
	}

	private static void firstLevelCachingInAction(SessionFactory sessionFactory) {
		create(sessionFactory);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		UserDetails11 user = session.get(UserDetails11.class, 6);
		UserDetails11 user2 = session.get(UserDetails11.class, 6);
		// there will be only one select query: because there is no change in
		// between both get

		session.getTransaction().commit();
		session.close();
		System.out.println("User id : " + user.getUserId() + ", user name : " + user.getUserName());

		session = sessionFactory.openSession();
		session.beginTransaction();

		user = session.get(UserDetails11.class, 6);
		user.setUserName("Updated user");
		user2 = session.get(UserDetails11.class, 6);
		// there will be one select and one update
		// this time no select again because, setUserName value will be in cache
		// so no need to pull up from database

		session.getTransaction().commit();
		session.close();
		System.out.println("User id : " + user.getUserId() + ", user name : " + user.getUserName());
	}

	private static void criteriaAPIProjection(SessionFactory sessionFactory) {
		// eg: retrieve max id in a table by criteria API
		create(sessionFactory);
		//getAllInAColumnByProjection(sessionFactory);
		//getMaxOfAColumnByProjection(sessionFactory);
		//getCountOfAColumnByProjection(sessionFactory);
		//orderResultByProjection(sessionFactory);
		queryingByExample(sessionFactory);
	}

	private static void queryingByExample(SessionFactory sessionFactory) {
		// when there is multiple properties needs to be checked against,
		// create result as example object and querying
		
		//queryByExampleSimple(sessionFactory);
		//queryByExampleExampleNotConsideringPrimaryKey(sessionFactory);
		//queryByExampleExcludeProperty(sessionFactory);
		queryByExampleLike(sessionFactory);
	}

	private static void queryByExampleLike(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		UserDetails11 exampleUser2 = new UserDetails11();
		exampleUser2.setUserName("User 1%");
		
		Example example2 = Example.create(exampleUser2).enableLike();

		Criteria criteria2 = session.createCriteria(UserDetails11.class).add(example2);

		List<UserDetails11> userDetails = (List<UserDetails11>) criteria2.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails11 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", user name : " + u.getUserName());
		}
	}

	private static void queryByExampleExcludeProperty(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		UserDetails11 exampleUser2 = new UserDetails11();
		exampleUser2.setUserId(5);
		
		Example example2 = Example.create(exampleUser2).excludeProperty("userName"); // hibernate will ignore this property from example

		Criteria criteria2 = session.createCriteria(UserDetails11.class).add(example2);

		List<UserDetails11> userDetails = (List<UserDetails11>) criteria2.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails11 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", user name : " + u.getUserName());
		}
	}

	private static void queryByExampleExampleNotConsideringPrimaryKey(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		UserDetails11 exampleUser2 = new UserDetails11();
		exampleUser2.setUserId(5);
		
		Example example2 = Example.create(exampleUser2);

		Criteria criteria2 = session.createCriteria(UserDetails11.class).add(example2);

		List<UserDetails11> userDetails = (List<UserDetails11>) criteria2.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails11 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", user name : " + u.getUserName());
		}
	}

	private static void queryByExampleSimple(SessionFactory sessionFactory) {
		UserDetails11 exampleUser = new UserDetails11();
		exampleUser.setUserId(5);
		exampleUser.setUserName("User 5"); // optional
		
		Example example = Example.create(exampleUser);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails11.class).add(example);

		List<UserDetails11> userDetails = (List<UserDetails11>) criteria.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails11 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", user name : " + u.getUserName());
		}
	}

	private static void orderResultByProjection(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails11.class).setProjection(Projections.property("userId"))
				.addOrder(Order.desc("userId")); // will return integer list of Ids
		//criteria = session.createCriteria(UserDetails11.class).addOrder(Order.desc("userId")); will return UserDetails list

		List<Integer> userIds = (List<Integer>) criteria.list();
		session.getTransaction().commit();
		session.close();
		for (Integer u : userIds) {
			System.out.println("User Id : " + u);
		}
	}

	private static void getCountOfAColumnByProjection(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails11.class).setProjection(Projections.count("userId"));

		List<Long> count = (List<Long>) criteria.list();
		session.getTransaction().commit();
		session.close();
		for (Long u : count) {
			System.out.println("User count : " + u);
		}
	}

	private static void getMaxOfAColumnByProjection(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails11.class).setProjection(Projections.max("userId"));

		List<Integer>userIds = (List<Integer>) criteria.list();
		session.getTransaction().commit();
		session.close();
		for (Integer u : userIds) {
			System.out.println("User id : " + u);
		}
	}

	private static void getAllInAColumnByProjection(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails11.class).setProjection(Projections.property("userId"));

		List<Integer> userIds = (List<Integer>) criteria.list();
		session.getTransaction().commit();
		session.close();
		for (Integer id : userIds) {
			System.out.println("User id : " + id);
		}
	}

	private static void criteriaAPIOR(SessionFactory sessionFactory) {
		create(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails11.class);
		criteria.add(Restrictions.or(Restrictions.like("userName", "%User 6%"), Restrictions.between("userId", 5, 10)));
		
		List<UserDetails11> userDetails = (List<UserDetails11>) criteria.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails11 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", User name : " + u.getUserName());
		}
	}

	private static void criteriaAPI(SessionFactory sessionFactory) {
		create(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails11.class);
		criteria.add(Restrictions.like("userName", "%User 6%")).add(Restrictions.between("userId", 5, 10));// similar to and clause when appending criterias by add

		List<UserDetails11> userDetails = (List<UserDetails11>) criteria.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails11 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", User name : " + u.getUserName());
		}
	}

	private static void namedNativeQuery(SessionFactory sessionFactory) {
		create12(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Query query = session.getNamedNativeQuery("User_Details12.byName");
		Query query = session.getNamedQuery("User_Details12.byName");
		query.setString(0, "User 6");
		List<UserDetails12> userDetails = (List<UserDetails12>) query.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails12 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", User name : " + u.getUserName());
		}
	}

	private static void namedQuery(SessionFactory sessionFactory) {
		create12(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.getNamedQuery("UserDetails12.byId");
		query.setInteger(0, 6);
		List<UserDetails12> userDetails = (List<UserDetails12>) query.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails12 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", User name : " + u.getUserName());
		}
	}

	private static void create12(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i = 0; i < 10; i++) {
			UserDetails12 userDetails = new UserDetails12();
			userDetails.setUserName("User " + (i + 1));
			session.save(userDetails);
		}
		session.getTransaction().commit();
		session.close();
	}

	private static void sqlInjectionSolutionByNamePlaceHolderSubstitution(SessionFactory sessionFactory) {
		create(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String minUserId = "5";
		String userNameToGet = "User 10";
		Query query = session.createQuery("from UserDetails11 where userId > :minUserId and userName = :userNameToGet");
		query.setInteger("minUserId", Integer.parseInt(minUserId));
		query.setString("userNameToGet", userNameToGet);
		List<UserDetails11> userDetails = (List<UserDetails11>) query.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails11 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", User name : " + u.getUserName());
		}
	}

	private static void sqlInjectionSolutionByPositionalPlaceHolderSubstitution(SessionFactory sessionFactory) {
		create(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String minUserId = "5";
		String userName = "User 10";
		Query query = session.createQuery("from UserDetails11 where userId > ? and userName = ?");
		query.setInteger(0, Integer.parseInt(minUserId));
		query.setString(1, userName);
		List<UserDetails11> userDetails = (List<UserDetails11>) query.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails11 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", User name : " + u.getUserName());
		}
	}

	private static void sqlInjection(SessionFactory sessionFactory) {
		create(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String minUserId = "5 or 1 = 1";
		Query query = session.createQuery("from UserDetails11 where userId > " + minUserId );
		List<UserDetails11> userDetails = (List<UserDetails11>) query.list();
		session.getTransaction().commit();
		session.close();
		for (UserDetails11 u : userDetails) {
			System.out.println("User id : " + u.getUserId() + ", User name : " + u.getUserName());
		}
	}

	private static void pagination(SessionFactory sessionFactory) {
		create(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from UserDetails11");
		query.setFirstResult(5); // result's offset will be taken from 6th (0, 1, 2, 3, 4, 5)
		query.setMaxResults(4); // number of results is 4
		List<UserDetails11> userDetails = (List<UserDetails11>)query.list();
		session.getTransaction().commit();
		session.close();
		for(UserDetails11 u : userDetails)
		{
			System.out.println("User id : " + u.getUserId() + ", User name : " + u.getUserName());
		}
	}

	private static void hQLAndQueryObject(SessionFactory sessionFactory) {
		create(sessionFactory);
		//readAllDataFromATable(sessionFactory);
		//System.out.println("******************************************************");
		//readDataFromATableWithCondition(sessionFactory);
		//System.out.println("******************************************************");
		//readOnlyOneColumnFromATable(sessionFactory);
		//System.out.println("******************************************************");
		//readMoreThanOneColumnFromATable(sessionFactory); // TODO
		//System.out.println("******************************************************");
		//readMoreThanOneColumnFromATableWithMap(sessionFactory); // TODO
		//System.out.println("******************************************************");
		//readMaxOfAColumn(sessionFactory);
	}

	private static void readMaxOfAColumn(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select max(userId) from UserDetails11");
		List maxId = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("Max user id : " + maxId.get(0));
	}

	private static void readMoreThanOneColumnFromATableWithMap(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select new map(userId, userName) from UserDetails11");
		List<Map<String, String>> userIdsAndNames = (List<Map<String, String>>) query.list();
		session.getTransaction().commit();
		session.close();

		for (int i = 0; i < userIdsAndNames.size(); i++) {
			Map<String, String> entry = userIdsAndNames.get(i);
			System.out.println(entry.get(i));
		}
	}

	private static void readMoreThanOneColumnFromATable(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select userId, userName from UserDetails11");
		List<List<String>> userIdsAndNames = (List<List<String>>) query.list();
		session.getTransaction().commit();
		session.close();

		for (int i = 0; i < userIdsAndNames.size(); i++) {
			List<String> entry = userIdsAndNames.get(i);
		}
	}

	private static void readOnlyOneColumnFromATable(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("select userName from UserDetails11");
		List<String> userNames = (List<String>) query.list();
		session.getTransaction().commit();
		session.close();

		for (String name : userNames) {
			System.out.println("User name : " + name);
		}
	}

	private static void readDataFromATableWithCondition(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from UserDetails11 where userId > 5");
		List<UserDetails11> userDetailsSub = (List<UserDetails11>) (query.list());
		session.getTransaction().commit();
		session.close();

		for (UserDetails11 u : userDetailsSub) {
			System.out.println("User id : " + u.getUserId() + ", User name : " + u.getUserName());
		}
	}

	private static void readAllDataFromATable(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from UserDetails11");
		List userDetails = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("Size of list result: " + userDetails.size());
	}

	private static void detachToPersistent(SessionFactory sessionFactory) {
		create(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails11 userDetails = session.get(UserDetails11.class, 2);
		session.getTransaction().commit();
		session.close();
		
		userDetails.setUserName("Updated user name after session close");
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(userDetails);
		userDetails.setUserName("Change after update");
		session.getTransaction().commit();
		session.close();
	}

	private static void updateAfterRetrive(SessionFactory sessionFactory) {
		create(sessionFactory);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails11 userDetails = session.get(UserDetails11.class, 2);
		userDetails.setUserName("Updated after retrieval");
		session.getTransaction().commit();
		session.close();
	}

	private static void updateAfterSave(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails11 userDetails = new UserDetails11();
		userDetails.setUserName("New user");
		session.save(userDetails);
		userDetails.setUserName("U1: Updated user");
		userDetails.setUserName("U2: Updated user");
		userDetails.setUserName("U3: Updated user");
		session.getTransaction().commit();
		userDetails.setUserName("U4: Updated user");
		session.close();
		userDetails.setUserName("U5: Updated user");
	}

	private static void handleCRUDOperations(SessionFactory sessionFactory) {
		create(sessionFactory);
		read(sessionFactory);
		update(sessionFactory);
		delete(sessionFactory);
	}

	private static void update(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails11 userDetails = session.get(UserDetails11.class, 5);
		userDetails.setUserName("Updated user");
		session.update(userDetails);
		session.getTransaction().commit();
		session.close();
	}

	private static void delete(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails11 userDetails = session.get(UserDetails11.class, 6);
		session.delete(userDetails);
		session.getTransaction().commit();
		session.close();
	}

	private static void read(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails11 userDetails = session.get(UserDetails11.class, 6);
		session.getTransaction().commit();
		session.close();

		System.out.println("User name pulled : " + userDetails.getUserName());
	}
	
	private static void create14(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i = 0; i < 10; i++) {
			UserDetails14 userDetails = new UserDetails14();
			userDetails.setUserName("User " + (i + 1));
			session.save(userDetails);
		}
		session.getTransaction().commit();
		session.close();
	}
	
	private static void create13(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i = 0; i < 10; i++) {
			UserDetails13 userDetails = new UserDetails13();
			userDetails.setUserName("User " + (i + 1));
			session.save(userDetails);
		}
		session.getTransaction().commit();
		session.close();
	}

	private static void create(SessionFactory sessionFactory) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i = 0; i < 10; i++) {
			UserDetails11 userDetails = new UserDetails11();
			userDetails.setUserName("User " + (i + 1));
			session.save(userDetails);
		}
		session.getTransaction().commit();
		session.close();
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