package uy.edu.um.db.serialization;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;

public class XstreamSaveToFileMain {

	public static void main(String[] args) throws Exception {
		XStream xstream = new XStream();

		xstream.alias("car", SchemaCar.class);

		SchemaVehicle car = new SchemaCar(1, "auto 1", "rojo","tipo");

		FileUtils.writeStringToFile(new File("schemaCar.xml"), 
				xstream.toXML(car), Charset.defaultCharset());

	}
}
