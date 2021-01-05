import Models.Modules;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless(name = "GradeUpdateEJB")
/**
 * Grade updating
 */
public class GradeUpdateBean {
    Models.Modules Modules = new Modules();

    public GradeUpdateBean() {
    }

    /**
     * Connection to DB
     */
    @EJB
    MongoClientProviderBean MongoClientProvider;

    /**
     * Add the grades for modules taken by the student
     * @param ID student id
     * @param Grade1 grade 1
     * @param Grade2 grade 2
     * @param Module module
     * @param teacherEmail lecturer email
     */
    public void updateGrade(String ID, String Grade1, String Grade2, String Module, String teacherEmail) {
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Student");

        DB db = mongo.getDB( "dbStudentRecords" );
        DBCollection coll = db.getCollection( "Collection_Student" );

        //teacher authentication - is the teacher on the course
        if(teacherCheck(teacherEmail, Module).equals(true)){

            // if module not taken then dont change anything and tell the user
            if(moduleCheck(Module, ID, coll).equals(true)){

                // adds the student grade for first assignment
                if (!Grade1.isEmpty()) {
                    collection.updateOne(Filters.eq("Student_ID", ID), Updates.set(Module + ".Grade_1", Grade1));
                }

                // adds the student grade for second assignment
                if (!Grade2.isEmpty()) {
                    collection.updateOne(Filters.eq("Student_ID", ID), Updates.set(Module + ".Grade_2", Grade2));
                }
            } else {
                // get the student list
                getStudentList();
            }
        }else {
            // get the student list
            getStudentList();
        }
    }

    /**
     * Return the student list
     * @return student list
     */
    public FindIterable<Document> getStudentList(){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Student");

        FindIterable<Document> colStudents = collection.find();

        // return the collection
        return colStudents;
    }

    /**
     * Check lecturer is in module
     * @param teacher lecturer
     * @param Module module
     * @return if the lecturer is in module
     */
    public Boolean teacherCheck(String teacher, String Module){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Lecturer");

        DB db = mongo.getDB( "dbStudentRecords" );
        DBCollection coll = db.getCollection( "Collection_Lecturer" );

        // check if the teacher is in the module
        if(moduleTeacherCheck(Module, teacher, coll).equals(true)){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Check if teacher is in module with query
     * @param Module module
     * @param teacherEmail teacher email
     * @param collection collection
     * @return boolean if the teacher is in the module
     */
    private Boolean moduleTeacherCheck(String Module, String teacherEmail, DBCollection collection) {
        // mongo query checking if the module is in the collection
        DBObject query = new BasicDBObject(Module,
                new BasicDBObject( "$ne", "")
                        .append("$exists", true))
                .append("Email", teacherEmail);
        return collection.count(query) == 1;
    }

    /**
     * Check if the module is taken
     * @param Module module
     * @param studentID student id
     * @param collection collection
     * @return if the module is taken by the student
     */
    private Boolean moduleCheck(String Module, String studentID, DBCollection collection) {
        // mongo query checking if the module is in the collection
        DBObject query = new BasicDBObject(Module,
                new BasicDBObject( "$ne", "")
                        .append("$exists", true))
                .append("Student_ID", studentID);
        return collection.count(query) == 1;
    }
}


