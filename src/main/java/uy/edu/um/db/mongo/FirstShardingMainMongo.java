package uy.edu.um.db.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FirstShardingMainMongo {

	public static void main(String[] args) {

		try (MongoClient mongoClient = new MongoClient(
				Arrays.asList(new ServerAddress("localhost", 27017), 
						new ServerAddress("localhost", 27021)))) {

			MongoDatabase database = mongoClient.getDatabase("test");

			MongoCollection<Document> collection = database.getCollection("transactions");

			int i = 11134981;
			for (int block = 0; block <= 10000; block++) {
				List<Document> documents = new ArrayList<>();
				for (int j = 0; j < 1000; j++)
					documents.add(new Document()
							.append("business", j+i)
							.append("amount", j+i));
					
				collection.insertMany(documents);
				i += 1000;
				System.out.println("i = " + i);
			}
			
		}
	}
}
