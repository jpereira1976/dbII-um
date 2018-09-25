package uy.edu.um.db.serialization;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchemaShowroom {
	List<SchemaVehicle> vehicles;
}
