import Models.ModuleModel;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import com.mongodb.client.MongoCursor;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "TeacherViewClass")
/**
 * Teacher view class
 */
public class TeacherViewClass extends HttpServlet {

    /**
     * Bean Connection
     */
    @EJB
    TeacherViewClassBean TeacherViewClassBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ModuleModel modulePasses = new ModuleModel();

        // get module collection
        FindIterable<Document> colStu = TeacherViewClassBean.getAll();

        MongoCursor<Document> cursor = colStu.iterator();

        // list of module passes
        ArrayList<ModuleModel> passesMod = new ArrayList<>();

        try{
            while(cursor.hasNext()){
                Document doc = (Document)cursor.next();
                // make module object
                ModuleModel moduleObj = new ModuleModel();

                // add module and pass count
                moduleObj.setModule_Code(doc.get("Module").toString());
                moduleObj.setPasses((Integer)doc.get("Passes"));

                // add to list
                passesMod.add(moduleObj);
            }

            // return the list of modules with pass count
            request.setAttribute("passes", passesMod);

        } finally {
            // dispatcher to teacher view jsp
            request.getRequestDispatcher("teacherView.jsp").forward(request, response);
            // cursor.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
