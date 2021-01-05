import Models.Modules;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless(name = "TeacherViewClassEJB")
/**
 * Teacher view class
 */
public class TeacherViewClassBean {
    // make module object
    Modules m = new Modules();

    public TeacherViewClassBean() {
    }

    /**
     * Connection to DB
     */
    @EJB
    MongoClientProviderBean MongoClientProvider;

    /**
     * Get the modules collection
     * @return modules
     */
    public FindIterable<Document> getAll(){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Module");
        FindIterable<Document> colModules = collection.find();

        // return modules collection
        return colModules;
    }



}
