import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HibernateExampleRepository implements ExampleRespository {
	@NonNull private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Example> allExamples() throws SQLException {
		try (Session session = sessionFactory.openSession()) {
			return (List<Example>) session.createQuery("from Example").list();			
		}
	}

}
