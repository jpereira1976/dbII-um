
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

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
        ds.setDefaultAutoCommit(true);

        Configuration configuration = new Configuration();
//        configuration.setProperty("hibernate.connection.url", databaseUrl);
//        configuration.setProperty("hibernate.connection.username", "root");
//        configuration.setProperty("hibernate.connection.password", "geocom");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        configuration.addAnnotatedClass(Example.class);
        //configuration.addResource("Example.hbm.xml");
        configuration.setProperty("hibernate.schema_update", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");
        
    	SessionFactory sessionFactory = configuration
    		    .buildSessionFactory(
    		        new StandardServiceRegistryBuilder()
    		            .applySettings(configuration.getProperties())
    		            .applySetting(Environment.DATASOURCE, ds)
    		            .build());
    	
    	try (Session session = sessionFactory.openSession()) {
    		Example example = session.get(Example.class, Integer.valueOf(1));
    		System.out.println("Hibernate " + example);
    	} 
    	HibernateExampleRepository hibernateRepository = new HibernateExampleRepository(sessionFactory);
    	
        JDBCTemplate template = new JDBCTemplate(ds);
		ExampleRespository jdbcRepository = 
				new JDBCExampleRespository(template);
		JDBCExample example = new JDBCExample(hibernateRepository);
		hibernateRepository.save(new Example(null, "Pepe", "Mi direccion"));
		//jdbcRepository.save(new Example(1, "Juan"));
		example.printExamples();	
		System.exit(0);
	}
	
	public void printExamples() throws SQLException {
		List<Example> result = repository.allExamples();
		
		result.forEach(example -> {
			System.out.println(example);
		});
		
	}
	
}
