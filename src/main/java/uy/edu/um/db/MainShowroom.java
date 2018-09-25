package uy.edu.um.db;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class MainShowroom {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String databaseUrl = "jdbc:mysql://localhost:3306/map?useSSL=false";
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(databaseUrl);
		ds.setUsername("root");
		ds.setPassword("geocom");
		ds.setDefaultAutoCommit(true);

		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
		configuration.addAnnotatedClass(Showroom.class);
		configuration.addAnnotatedClass(Car.class);
		configuration.setProperty("hibernate.schema_update", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("hibernate.show_sql", "true");

		SessionFactory sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).applySetting(Environment.DATASOURCE, ds).build());

		try (Session session = sessionFactory.openSession()) {
			Query query = session.createQuery("SELECT c from uy.edu.um.db.Showroom s"
					+ " join s.cars c "
					+ "where s.id = :showroomId and c.color = :color");
			query.setParameter("color", "rojo");
			query.setParameter("showroomId", 1);
			@SuppressWarnings("unchecked")
			List<Car> cars = (List<Car>) query.getResultList();
			cars.forEach(car -> System.out.println(car));
		}
		
//		try (Session session = sessionFactory.openSession()) {
//			session.beginTransaction();
//			Showroom showroom = new Showroom(
//					null, "manager2", "location2",
//					Arrays.asList(
//							new Car(null, "auto1", "rojo"), 
//							new Car(null, "auto2", "azul")));
//			session.save(showroom);
//			session.getTransaction().commit();
//			System.out.println(showroom);
//		}
//		try (Session session = sessionFactory.openSession()) {
//			session.beginTransaction();
//			
//			Showroom showroom = session.get(Showroom.class, 2);
//			Car car = showroom.getCars().remove(0);
//			//session.saveOrUpdate(showroom);
//			System.out.println(showroom);
//			
//			System.out.println(car);
//			
//			session.getTransaction().commit();
//		}
	}
}
