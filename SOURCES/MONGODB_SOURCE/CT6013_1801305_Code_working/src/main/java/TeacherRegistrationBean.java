import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless(name = "TeacherRegistrationEJB")
/**
 * Lecturer registration
 */
public class TeacherRegistrationBean {
    public TeacherRegistrationBean() {
    }

    /**
     * Connection to DB
     */
    @EJB
    MongoClientProviderBean MongoClientProvider;

    /**
     * Create teacher in the db
     * @param teacher Lecturer document
     */
    public void createTeacher(Document teacher) {
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Lecturer");

        // insert lecturer
        collection.insertOne(teacher);
    }

    /**
     * get the list of lecturers from db
     * @return lecturers list
     */
    public FindIterable<Document> getTeacherList(){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Lecturer");

        FindIterable<Document> colTeacher = collection.find();

        // return the collection of lecturers
        return colTeacher;
    }
}
