package uy.edu.um.db;

import java.sql.SQLException;
import java.util.List;

public class JDBCExample {
	ExampleRespository repository;

	public JDBCExample(ExampleRespository repository) {
		this.repository = repository;
	}
	
	public void printExamples() throws SQLException {
		List<Example> result = repository.allExamples();
		
		result.forEach(example -> {
			System.out.println(example);
		});
		
	}
	
}
