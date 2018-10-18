package uy.edu.um.db.mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class FirstMainMongo {

	public static void main(String[] args) {
		
		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {

			MongoDatabase database = mongoClient.getDatabase("test");
			
			MongoCollection<Document> collection = database.getCollection("students");

			FindIterable<Document> iterable = 
					collection
						.find(new Document("name", "Juan"))
						.projection(new Document("age", 1).append("_id", 0));
			
			
			MongoCursor<Document> cursor = iterable.iterator();
			while (cursor.hasNext()) {
				Document document = cursor.next();
				System.out.println(document.toJson());
			}
			
			FindIterable<Document> iterable2 = 
					collection
						.find(Document.parse("{name:\"Juan\"}"))
						.projection(Document.parse("{age: 1, _id : 0}"));
			
			MongoCursor<Document> cursor2 = iterable2.iterator();
			while (cursor2.hasNext()) {
				Document document = cursor2.next();
				System.out.println(document.getDouble("age"));
				System.out.println(document.toJson());
			}

		}
	}
}
