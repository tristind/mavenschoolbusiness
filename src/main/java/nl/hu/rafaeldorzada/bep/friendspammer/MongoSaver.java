package nl.hu.rafaeldorzada.bep.friendspammer;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.UnknownHostException;

public class MongoSaver {
	
	public static boolean saveEmail(String to, String from, String subject, String text, Boolean html) {
		Logger logger = LoggerFactory.getLogger(MongoSaver.class);
		String userName = "test";
		String password = "test";
		String database = "school";
		
		MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
		
		boolean success = true;
		
		try (MongoClient mongoClient = new MongoClient(new ServerAddress("ds117540.mlab.com:17540/school", 27939), credential, MongoClientOptions.builder().build()) ) {
			
			MongoDatabase db = mongoClient.getDatabase( database );
			
			MongoCollection<Document> c = db.getCollection("email");
			
			Document  doc = new Document ("to", to)
			        .append("from", from)
			        .append("subject", subject)
			        .append("text", text)
			        .append("asHtml", html);
			c.insertOne(doc);
		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			mongoException.printStackTrace();
			success = false;
		}
		
		return success;
 		
	}
	
	
	public static void main(String ...args) {
		Logger logger = LoggerFactory.getLogger("test");
		logger.info("test");
	}

}
