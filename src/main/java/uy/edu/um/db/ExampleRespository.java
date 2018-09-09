package uy.edu.um.db;
import java.sql.SQLException;
import java.util.List;

public interface ExampleRespository {

	List<Example> allExamples() throws SQLException;

	void save(Example example) throws SQLException;

}