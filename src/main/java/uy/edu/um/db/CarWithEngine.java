package uy.edu.um.db;
import lombok.Data;

@Data
public class CarWithEngine {
	private Integer id;
	private String name;
	private String color;
	private Engine engine;
}
