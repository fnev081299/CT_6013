import Models.Modules;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless(name = "RegistrationEJB")
/**
 * Student Registration
 */
public class RegistrationBean {
    Models.Modules Modules = new Modules();

    public RegistrationBean() {
    }

    /**
     * Connection to DB
     */
    @EJB
    MongoClientProviderBean MongoClientProvider;

    /**
     * Create student in the db
     * @param student student document
     */
    public void createStudent(Document student) {
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Student");

        // insert student
        collection.insertOne(student);
    }

    /**
     *  Get the list of students
     * @return List of students
     */
    public FindIterable<Document> getStudentList(){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Student");

        FindIterable<Document> colStudents = collection.find();

        // return the student collection
        return colStudents;
    }

    /**
     * Check if the student is the correct year for the module
     * @param yearG year group
     * @param module module
     * @return if the student is in the year group for the module
     */
    public Boolean yearCheck(String yearG, String module){
        // get the year group and the course year group
        String yearGroup = String.valueOf(yearG.charAt(0));
        String course = String.valueOf(module.charAt(2));

        // check if the year group and the course year group match
        // first year
        if(yearGroup.equals("1") && !course.equals("-") && course.equals("4")){
            return true;
        }
        // second year
        if(yearGroup.equals("2") && !course.equals("-") && course.equals("5")){
            return true;
        }
        // third year
        if(yearGroup.equals("3") && !course.equals("-") && course.equals("6")){
            return true;
        }

        return false;
    }
}
