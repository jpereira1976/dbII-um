package uy.edu.um.db.serialization;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class SchemaCar extends SchemaVehicle {
	String description;
	String type;
	
	public SchemaCar(Integer id, String color, String description, String type) {
		super(id, color);
		this.description = description;
		this.type = type;
	}
	
	
}
