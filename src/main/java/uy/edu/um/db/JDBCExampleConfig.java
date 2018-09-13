package uy.edu.um.db;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:jdbc.properties")
public class JDBCExampleConfig {
	@Value("${jdbc.url}")
	String jdbcUrl;
	@Value("${jdbc.user}")
	String jdbcUser;
	@Value("${jdbc.pwd:geocom}")
	String jdbcPwd;
	
	
	@Bean(name="pool")
	DataSource pool() {
		BasicDataSource ds = new BasicDataSource();
        ds.setUrl(jdbcUrl);
        ds.setUsername(jdbcUser);
        ds.setPassword(jdbcPwd);
        ds.setDefaultAutoCommit(true);
        return ds;
	}

	@Bean
	SessionFactory sessionFactory(@Autowired @Qualifier("pool") DataSource ds) {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        configuration.addAnnotatedClass(Example.class);
        //configuration.addResource("Example.hbm.xml");
        configuration.setProperty("hibernate.schema_update", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");
        
    	return configuration
    		    .buildSessionFactory(
    		        new StandardServiceRegistryBuilder()
    		            .applySettings(configuration.getProperties())
    		            .applySetting(Environment.DATASOURCE, ds)
    		            .build());
		
	}
	
	@Bean
	JDBCExample example(@Autowired ExampleRespository repository) { 
		return new JDBCExample(repository);
	}
	
	
}
