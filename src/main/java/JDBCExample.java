
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class JDBCExample {
	ExampleRespository repository = new JDBCExampleRespository();
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		new JDBCExample().printExamples();		
	}
	
	public void printExamples() throws SQLException {
		List<Example> result = repository.allExamples();
		
		result.forEach(example -> {
			System.out.println(example);
		});
		
	}
	
}
