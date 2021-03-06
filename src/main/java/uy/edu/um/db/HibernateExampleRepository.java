package uy.edu.um.db;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
@Profile("hibernate")
public class HibernateExampleRepository implements ExampleRespository {
	@Autowired
	@NonNull private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Example> allExamples() throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			return (List<Example>) session.createQuery("from Example").list();			
		}
	}

	@Override
	public void save(Example example) throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			
			session.save(example);		
			
			session.getTransaction().commit();
		}		
	}

}
