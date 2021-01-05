import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.sun.corba.se.spi.ior.ObjectId;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import static com.mongodb.client.model.Filters.eq;

@Stateless(name = "TeacherViewStudentEJB")
/**
 * Teacher view student
 */
public class TeacherViewStudentBean {
    public TeacherViewStudentBean() {
    }

    /**
     * Connection to DB
     */
    @EJB
    MongoClientProviderBean MongoClientProvider;

    /**
     * Get the student from the db
     * @param studentID student id
     * @param teacherEmail lecturer email
     * @param courseCode course code
     * @return student
     */
    public Document getStudent(String studentID, String teacherEmail, String courseCode){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Student");

        // check teacher is on module
        if(teacherModuleCheck(teacherEmail, courseCode).equals(true)){
            Document document = collection.find(eq("Student_ID", studentID)).first();

            // if the course key exists in the student document return the document
            if(document.containsKey(courseCode)){
                return document;
            }
            return null;
        } else{
            return null;
        }
    }

    /**
     * Check if the lecturer is on the module
     * @param teacher lecturer
     * @param Module module
     * @return if the lecturer is in the module
     */
    public Boolean teacherModuleCheck(String teacher, String Module){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Lecturer");

        DB db = mongo.getDB( "dbStudentRecords" );
        DBCollection coll = db.getCollection( "Collection_Lecturer" );

        // return boolean value based on whether the lecturer document contains the module
        if(moduleTeacherCheck(Module, teacher, coll).equals(true)){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Mongo query for the checking if the lecturer has the module
     * @param Module  module
     * @param teacherEmail lecturer email
     * @param collection lecturer collection
     * @return if the lecturer is in the module
     */
    private Boolean moduleTeacherCheck(String Module, String teacherEmail, DBCollection collection) {
        // mongo query seeing if the module exists in document
        DBObject query = new BasicDBObject(Module,
                new BasicDBObject( "$ne", "")
                        .append("$exists", true))
                .append("Email", teacherEmail);
        // return the boolean value from query
        return collection.count(query) == 1;
    }
}
