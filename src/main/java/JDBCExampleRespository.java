import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JDBCExampleRespository implements ExampleRespository {
	JDBCTemplate template;

	public JDBCExampleRespository(JDBCTemplate template) {
		this.template = template;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Example> allExamples() throws SQLException {
		
		return (List<Example>)template.execute(con -> {
				List<Example> result = new ArrayList<>();
				PreparedStatement stmt = con.prepareStatement(
						"select * from example");
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
					result.add(new Example(rs.getInt("id"), rs.getString("nombre")));  
			return result;
		});
		
	}
	
	public void save(Example example) throws SQLException {
		template.execute(con -> {
			PreparedStatement stmt = con.prepareStatement(
					"insert into example (id, name) values(?,?)");
			stmt.setInt(1, example.getId());
			stmt.setString(2, example.getName());
			return stmt.execute(); 
		});
	}
}
