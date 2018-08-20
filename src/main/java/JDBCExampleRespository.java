import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JDBCExampleRespository implements ExampleRespository {

	@Override
	public List<Example> allExamples() throws SQLException {
		List<Example> result = new ArrayList<>();
		
		try (Connection con = DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/map?useSSL=false","root","geocom")) { 
		
			PreparedStatement stmt = con.prepareStatement(
					"select * from example");
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())  
				result.add(new Example(rs.getInt("id"), rs.getString("nombre")));  
		}
		return result;
	}
}
