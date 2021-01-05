import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless(name = "StudentHomeEJB")
/**
 * Student home
 */
public class StudentHomeBean {
    public StudentHomeBean() {
    }

    /**
     * Connection to DB
     */
    @EJB
    MongoClientProviderBean MongoClientProvider;

    /**
     * Return the student information with their student id as the key for finding the student
     * @param studentID student id
     * @return student information
     */
    public FindIterable<Document> getStudentInfo(String studentID){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Student");

        Document x = new Document();
        x.put("Student_ID", studentID);

        // find the document in the collection
        FindIterable<Document> colStudent = collection.find(x);

        // return the student from the collection
        return colStudent;
    }

}
