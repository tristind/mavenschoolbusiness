package nl.hu.rafaeldorzada.bep.friendspammer;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class MongoSaver {

	public static boolean saveEmail(String to, String from, String subject, String text, Boolean html) {
		Logger logger = LoggerFactory.getLogger(MongoSaver.class);
		boolean success = true;
		
		try {
			MongoConfig mongoconfig = new MongoConfig();
			MongoCollection<Document> c = mongoconfig.getConnection().getCollection("email");
			
			Document  doc = new Document ("to", to)
			        .append("from", from)
			        .append("subject", subject)
			        .append("text", text)
			        .append("asHtml", html);
			c.insertOne(doc);
		} catch (MongoException mongoException) {
			logger.info("XXXXXXXXXXXXXXXXXX ERROR WHILE SAVING TO MONGO XXXXXXXXXXXXXXXXXXXXXXXXXX");
			logger.info(mongoException.getMessage());
			success = false;
		}
		
		return success;
 		
	}

	public Iterator<Document> getAllMessages() {
		MongoConfig mongoconfig = new MongoConfig();
		MongoCollection<Document> c = mongoconfig.getConnection().getCollection("email");

		return c.find().iterator();

	}

	public static void main(String ...args) {
		Logger logger = LoggerFactory.getLogger("test");
		logger.info("test");
	}

}
