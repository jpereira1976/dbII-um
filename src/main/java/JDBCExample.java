
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class JDBCExample {
	ExampleRespository repository;

	public JDBCExample(ExampleRespository repository) {
		this.repository = repository;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		String databaseUrl = "jdbc:mysql://localhost:3306/map?useSSL=false";
		BasicDataSource ds = new BasicDataSource();
        ds.setUrl(databaseUrl);
        ds.setUsername("root");
        ds.setPassword("geocom");

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", databaseUrl);
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "geocom");
        configuration.addAnnotatedClass(Example.class);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.configure();


        ServiceRegistry registry
        = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
    	SessionFactory sessionFactory = null;
    	try {
    		sessionFactory = configuration.buildSessionFactory(registry);
    	}
    	catch (Exception e) {
    		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
    		// so destroy it manually.
    		StandardServiceRegistryBuilder.destroy( registry );

    	}
    	
    	try (Session session = sessionFactory.openSession()) {
    		Example example = session.get(Example.class, Integer.valueOf(1));
    		System.out.println("Hibernate " + example);
    	} 
    	HibernateExampleRepository hibernateRepository = new HibernateExampleRepository(sessionFactory);
    	
        JDBCTemplate template = new JDBCTemplate(ds);
		JDBCExampleRespository jdbcRepository = 
				new JDBCExampleRespository(template);
		new JDBCExample(hibernateRepository).printExamples();	
		System.exit(0);
	}
	
	public void printExamples() throws SQLException {
		List<Example> result = repository.allExamples();
		
		result.forEach(example -> {
			System.out.println(example);
		});
		
	}
	
}
