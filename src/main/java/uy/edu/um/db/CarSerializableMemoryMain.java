package uy.edu.um.db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CarSerializableMemoryMain {

	public static void main(String[] args) throws Exception {
		
		Car car = new Car(1, "auto 1", "rojo");
		
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bo);
		oos.writeObject(car);
		
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bi);
		
		Car newCar = (Car)ois.readObject();
		
		System.out.println("Car    -> " + car);
		System.out.println("newCar -> " + newCar);
		System.out.println("Car instance not newCarInstance: " + (car != newCar));
	
				
		
	}
}
