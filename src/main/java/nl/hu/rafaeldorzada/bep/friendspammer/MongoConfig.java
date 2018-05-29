package nl.hu.rafaeldorzada.bep.friendspammer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoConfig {
    private final String username = "test";
    private final String password = "test";
    private final String database = "school";
    private final static ServerAddress serveraddress = new ServerAddress("ds117540.mlab.com:17540/school", 27939);

    public MongoDatabase getConnection() {
        MongoCredential credential = MongoCredential.createCredential(username, password, database.toCharArray());
        return new MongoClient(serveraddress, credential, MongoClientOptions.builder().build()).getDatabase(database);
    }


}
