import java.sql.SQLException;
import java.util.List;

public interface ExampleRespository {

	List<Example> allExamples() throws SQLException;

}