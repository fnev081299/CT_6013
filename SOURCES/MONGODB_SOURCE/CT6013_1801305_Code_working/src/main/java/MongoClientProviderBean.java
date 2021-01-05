import javax.annotation.PostConstruct;
import javax.ejb.*;

import com.mongodb.MongoClient;

@Singleton(name = "MongoClientProviderEJB")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
/**
 * Connection to DB
 */
public class MongoClientProviderBean {
    public MongoClientProviderBean() {
    }

    private MongoClient mongoClient = null;

    @Lock(LockType.READ)
    public MongoClient getMongoClient(){
        return mongoClient;
    }

    @PostConstruct
    public void init(){
        // local host and port information
        String mongoIPAddress = "localhost";
        Integer mongoPort = 27017;
        mongoClient = new MongoClient(mongoIPAddress, mongoPort);
    }
}