package uy.edu.um.db;
import lombok.Data;

@Data
public class Engine {
	private Integer id;
	private String make;
	private String model;
	private String size;
	private CarWithEngine car;
}
