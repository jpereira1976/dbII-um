package uy.edu.um.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CarSerializableFileMain {

	public static void main(String[] args) throws Exception {
		
		Car car = new Car(1, "auto 1", "rojo");
		
		FileOutputStream bo = new FileOutputStream("car.ser");
		ObjectOutputStream oos = new ObjectOutputStream(bo);
		oos.writeObject(car);
		
		FileInputStream bi = new FileInputStream("car.ser");
		ObjectInputStream ois = new ObjectInputStream(bi);
		
		Car newCar = (Car)ois.readObject();
		
		System.out.println("Car    -> " + car);
		System.out.println("newCar -> " + newCar);
		System.out.println("Car instance not newCarInstance: " + (car != newCar));
	
				
		
	}
}
