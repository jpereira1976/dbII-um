package uy.edu.um.db;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
