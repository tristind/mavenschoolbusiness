package nl.hu.rafaeldorzada.bep.friendspammer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoConfig {
    private String username = "test";
    private String password = "test";
    private String database = "school";
    private ServerAddress serveraddress = new ServerAddress("ds117540.mlab.com:17540/school", 27939);

    public MongoDatabase getConnection() {
        MongoCredential credential = MongoCredential.createCredential(username, password, database.toCharArray());
        MongoDatabase db = new MongoClient(serveraddress, credential, MongoClientOptions.builder().build()).getDatabase(database);
        return db;
    }


}
