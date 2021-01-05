import Models.Modules;
import Models.StudentGradeModel;
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

@WebServlet(name = "StudentHome")
/**
 * Student home
 */
public class StudentHome extends HttpServlet {

    /**
     * Bean Connection
     */
    @EJB
    StudentHomeBean StudentHomeBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // get the student id
        String studentID = request.getParameter("student_id");

        // get the student information
        FindIterable<Document> colStudent = StudentHomeBean.getStudentInfo(studentID);

        Modules allModules = new Modules();
        MongoCursor<Document> cursor = colStudent.iterator();

        // make grades list
        ArrayList<StudentGradeModel> grades = new ArrayList<>();

        try{
            while(cursor.hasNext()){
                Document doc = (Document)cursor.next();
                request.setAttribute("student", doc.get("Student_ID"));

                // statement to get the specific modules they are on to output
                ArrayList<String> modules = allModules.allModules();

                // run through the modules to check if they are taken
                for(int i = 0; i < modules.size(); i++){
                    // check if the module is taken and return the values for the module
                    if(doc.containsKey(modules.get(i))){
                        Document m = (Document) doc.get(modules.get(i));
                        StudentGradeModel gradeObj = new StudentGradeModel();

                        gradeObj.setModuleName(m.getString("Course_Name"));
                        gradeObj.setModule_Code(modules.get(i));

                        String gr1 = m.getString("Grade_1");
                        String gr2 = m.getString("Grade_2");

                        if(gr1.equals("-")){
                            gr1 = "0";
                        }

                        if(gr2.equals("-")){
                            gr2 = "0";
                        }

                        int g1 = Integer.parseInt(gr1);
                        int g2 = Integer.parseInt(gr2);

                        gradeObj.setGrade1(g1);
                        gradeObj.setGrade2(g2);

                        // add the object to the grades list
                        grades.add(gradeObj);
                    }
                }

                // set the grades list attribute
                request.setAttribute("grades", grades);
            }
        } finally {
            // sent the requests to jsp
            request.getRequestDispatcher("student_home.jsp").forward(request, response);
            cursor.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
