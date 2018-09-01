import java.util.Arrays;

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
		configuration.addResource("ShowroomCars.hbm.xml");
		configuration.setProperty("hibernate.schema_update", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("hibernate.show_sql", "true");

		SessionFactory sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).applySetting(Environment.DATASOURCE, ds).build());

//		try (Session session = sessionFactory.openSession()) {
//			session.beginTransaction();
//			session.save(new Showroom(
//					null, "manager", "location",
//					Arrays.asList(
//							new Car(null, "auto1", "rojo"), 
//							new Car(null, "auto2", "azul"))));
//			session.getTransaction().commit();
//		}
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			
			Showroom showroom = session.get(Showroom.class, 1);
			showroom.getCars().remove(0);
			
			session.saveOrUpdate(showroom);
			
			session.getTransaction().commit();
		}
	}
}
