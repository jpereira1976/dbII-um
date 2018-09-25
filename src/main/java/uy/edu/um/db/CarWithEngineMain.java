package uy.edu.um.db;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class CarWithEngineMain {
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String databaseUrl = "jdbc:mysql://localhost:3306/cars?useSSL=false";
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(databaseUrl);
		ds.setUsername("root");
		ds.setPassword("geocom");
		ds.setDefaultAutoCommit(true);

		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
		configuration.addAnnotatedClass(CarWithEngine.class);
		configuration.addAnnotatedClass(Engine.class);
		configuration.setProperty("hibernate.schema_update", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("hibernate.show_sql", "true");

		SessionFactory sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).applySetting(Environment.DATASOURCE, ds).build());
		
		try (Session session = sessionFactory.openSession()) {
			session.getTransaction().begin();
			CarWithEngine car = new CarWithEngine(null, "my car", "black", 
					new Engine(null, "2010", "Toyota", "mid"));
			session.save(car);
			
			Query query = session.createQuery("select c from uy.edu.um.db.CarWithEngine c "
					+ "where c.engine.id = :engineId");
			query.setParameter("engineId", 2);
			@SuppressWarnings("unchecked")
			List<CarWithEngine> cars = query.getResultList();
			cars.forEach(kar -> System.out.println(kar));
			
			session.getTransaction().commit();
		}
	}
	
}
