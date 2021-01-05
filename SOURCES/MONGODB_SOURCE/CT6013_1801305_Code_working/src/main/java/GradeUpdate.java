import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
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

@WebServlet(name = "GradeUpdate")
/**
 * Grade updating
 */
public class GradeUpdate extends HttpServlet {
    /**
     * Connecting Bean
     */
    @EJB
    GradeUpdateBean GradeUpdateBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        FindIterable<Document> colStu = GradeUpdateBean.getStudentList();
        MongoCursor<Document> cursor = colStu.iterator();

        // getting the information from jsp
        String Teacher_Email = request.getParameter("teacher_email");
        String Student_ID = request.getParameter("student_id");
        String Module = request.getParameter("selection1");
        String Student_Grade1 = request.getParameter("grade1");
        String Student_Grade2 = request.getParameter("grade2");

        // update grade
        GradeUpdateBean.updateGrade(Student_ID, Student_Grade1, Student_Grade2, Module, Teacher_Email);

        try{
            // redirect home
            response.sendRedirect("/teacherView.jsp");
        } finally {
            cursor.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
