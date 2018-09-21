package uy.edu.um.db;

import com.thoughtworks.xstream.XStream;

public class XstreamMain {
	
	public static void main(String[] args) {
		XStream xstream = new XStream();
		
		xstream.alias("car", Car.class);
		
		Car car = new Car(1, "auto 1", "rojo");
		
		String xml = xstream.toXML(car);
				
		System.out.println(xml);
	
		Car newCar = (Car) xstream.fromXML(xml);
		
		System.out.println(newCar);
	}
}
