package uy.edu.um.db;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JDBCTemplate {
	DataSource ds;
	
	public JDBCTemplate(DataSource ds) {
		this.ds = ds;
	}
	
	Object execute(JDBCTemplateCallback callback) throws SQLException {
		try (Connection con = ds.getConnection()) { 
			return callback.execute(con);
		}		
	}
}
