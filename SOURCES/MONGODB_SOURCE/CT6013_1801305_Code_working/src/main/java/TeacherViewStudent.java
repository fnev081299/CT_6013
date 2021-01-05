import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TeacherViewStudent")
/**
 * Teacher view student
 */
public class TeacherViewStudent extends HttpServlet {

    /**
     * Bean Connection
     */
    @EJB
    TeacherViewStudentBean TeacherViewStudentBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // get values from user in jsp
        String email = request.getParameter("teacher_email");
        String classCode = request.getParameter("selection1");
        String studentID = request.getParameter("student_ID");

        try{
            // get student from db
            Document docStudent = TeacherViewStudentBean.getStudent(studentID, email, classCode);

            // set attribute to student number
            request.setAttribute("studentNumber", docStudent.get("Student_ID"));

            // make module document
            Document m = (Document) docStudent.get(classCode);

            // set module and grade attributes
            request.setAttribute("module",m.get("Course_Name"));
            request.setAttribute("courseCode",classCode);
            request.setAttribute("grade1",m.get("Grade_1"));
            request.setAttribute("grade2",m.get("Grade_2"));

            // dispatcher to teacher view specific jsp
            request.getRequestDispatcher("teacherViewSpecific.jsp").forward(request, response);

        } catch(NullPointerException e) {
            out.println("Sorry, you dont have access to this module.....");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
