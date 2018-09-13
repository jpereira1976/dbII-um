package uy.edu.um.db;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JDBCTemplate {
	@Autowired
	DataSource ds;
		
	Object execute(JDBCTemplateCallback callback) throws SQLException {
		try (Connection con = ds.getConnection()) { 
			return callback.execute(con);
		}		
	}
}
