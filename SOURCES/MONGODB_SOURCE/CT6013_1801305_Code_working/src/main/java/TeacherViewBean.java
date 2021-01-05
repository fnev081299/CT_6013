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

@Stateless(name = "TeacherViewEJB")
/**
 * Teacher view
 */
public class TeacherViewBean {
    public TeacherViewBean() {
    }

    /**
     * Connection to DB
     */
    @EJB
    MongoClientProviderBean MongoClientProvider;

    /**
     * Gets students for db
     * @param email email
     * @param courseCode course code
     * @return students
     */
    public FindIterable<Document> getAllStudents(String email, String courseCode){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Student");

        // check teacher is on module
        if(teacherModuleCheck(email, courseCode).equals(true)){

            // get student collection
            FindIterable<Document> colStudent = collection.find();

            // return student collection
            return colStudent;
        } else{
            return null;
        }
    }

    /**
     * Check if lecturer is in module
     * @param teacher lecturer
     * @param Module module
     * @return if lecturer is in module
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

        // return true if the lecturer is in the module
        if(moduleTeacherCheck(Module, teacher, coll).equals(true)){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Mongo query for checking the lecturer is in module
     * @param Module module
     * @param teacherEmail lecturer email
     * @param collection lecturer collection
     * @return if the lecturer is in module
     */
    private Boolean moduleTeacherCheck(String Module, String teacherEmail, DBCollection collection) {
        // check if module exists in the lecturer document
        DBObject query = new BasicDBObject(Module,
                new BasicDBObject( "$ne", "")
                        .append("$exists", true))
                .append("Email", teacherEmail);
        // return true/false boolean value
        return collection.count(query) == 1;
    }

    /**
     * Add passes in module
     * @param module module
     * @param passCount pass count
     */
    public void addModulePasses(String module, Integer passCount){
        // creating a mongo client
        MongoClient mongo = MongoClientProvider.getMongoClient();

        // accessing the database
        MongoDatabase database = mongo.getDatabase("dbStudentRecords");

        // retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Collection_Module");

        Document ModulePass = new Document();
        ModulePass.put("Module", module);
        ModulePass.put("Passes", passCount);

        // check if the value has changed compared to the one in the collection and if not make it else update it
        DB db = mongo.getDB( "dbStudentRecords" );
        DBCollection coll = db.getCollection( "Collection_Module" );

        try{
            // check if module exists
            Document document = collection.find(eq("Module", module)).first();

            if(document.isEmpty()){
                // insert student
                collection.insertOne(ModulePass);
            } else {
                // update
                collection.updateOne(Filters.eq("Module", module), Updates.set("Passes", passCount));
            }
        } catch( NullPointerException e){
            collection.insertOne(ModulePass);
        }
    }

    // check if module exists

    /**
     * Check if the module exists in the DB
     * @param Module module
     * @param collection collection
     * @return if module exists in the collection
     */
    private Boolean moduleCheck(String Module, DBCollection collection) {
        // mongo query checking if module exists in the collection
        DBObject query = new BasicDBObject(Module,
                new BasicDBObject( "$ne", "")
                        .append("$exists", true))
                        .append("Module", Module);
        // return boolean value if in collection return true
        return collection.count(query) == 1;
    }
}
