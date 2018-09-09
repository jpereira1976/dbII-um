package uy.edu.um.db;
import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringJDBCExampleMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(JDBCExampleConfig.class);
		JDBCExample example = ctx.getBean(JDBCExample.class);
		example.printExamples();
		System.exit(0);
	}
}
