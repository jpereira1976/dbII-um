package uy.edu.um.db.serialization;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainSchemaShowroomSerialization {

	public static void main(String[] args) throws Exception {
		List<SchemaVehicle> vehicles = new ArrayList<>();
		vehicles.add(new SchemaVehicle(1, "azul"));
		SchemaCar car = new SchemaCar(2, "rojo", "especial", "Mercedes");
		vehicles.add(car);
		vehicles.add(car);
		SchemaShowroom showroom = new SchemaShowroom(vehicles);
		
		ObjectMapper mapper = new ObjectMapper();
		//mapper.enableDefaultTyping();
		String jsonShowroom = mapper.writeValueAsString(showroom);
		System.out.println(jsonShowroom);

		SchemaShowroom showroomReaded = mapper.readValue(jsonShowroom, SchemaShowroom.class);
		
		System.out.println(showroomReaded);
		
		System.out.println("Same car instance? " + (showroomReaded.getVehicles().get(1) == showroomReaded.getVehicles().get(2)));
		
		
	}
}
