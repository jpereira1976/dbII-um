package uy.edu.um.db.serialization;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;

public class XstreamReadFromFileMain {

	public static void main(String[] args) throws Exception {
		String xml = FileUtils.readFileToString(new File("schemaCar.xml"), Charset.defaultCharset());
		
		XStream xstream = new XStream();
		
		xstream.alias("car", SchemaCar.class);

		SchemaVehicle car = (SchemaVehicle)xstream.fromXML(xml);
		
		System.out.println(car);
	}
}
