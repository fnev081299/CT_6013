import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.print.Doc;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;

@Stateless(name = "LoginEJB")
/**
 * Login user
 */
public class LoginBean {
    public LoginBean() {
    }

    /**
     * Connection to DB
     */
    @EJB
    MongoClientProviderBean MongoClientProvider;

    /**
     * Check the login values for the user
     * @param email email
     * @param password password
     * @param personType person type
     * @return if the user login is valid
     */
    public Boolean loginCheck(String email, String password, String personType){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // if teacher or student
        if(personType.equals("teacher")){
            // retrieving a collection
            MongoCollection<Document> collection = database.getCollection("Collection_Lecturer");

            // find teacher
            Document document = collection.find(eq("Email", email)).first();

            // get their password
            if(document.get("Password").equals(password)){
                return true;
            } else {
                return false;
            }

        } else if(personType.equals("student")){
            // retrieving a collection
            MongoCollection<Document> collection = database.getCollection("Collection_Student");

            // find student
            Document student = new Document();
            student.put("email", email);

            // find teacher
            Document document = collection.find(eq("Email", email)).first();

            // get their password
            if(document.get("Password").equals(password)){
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    /**
     * Get the user
     * @param typePerson person type
     * @param email email
     * @return user document
     */
    public FindIterable<Document> getPerson(String typePerson, String email){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        Document x = new Document();
        x.put("Email", email);

        if(typePerson.equals("teacher")){
            // retrieving a collection
            MongoCollection<Document> collection = database.getCollection("Collection_Lecturer");

            FindIterable<Document> colTeacher = collection.find(x);

            // teacher collection
            return colTeacher;

        } else if(typePerson.equals("student")){
            // retrieving a collection
            MongoCollection<Document> collection = database.getCollection("Collection_Student");

            FindIterable<Document> colStudent = collection.find(x);

            // teacher collection
            return colStudent;
        }
        return null;
    }

}
