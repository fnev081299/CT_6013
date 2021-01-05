import Models.Modules;
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

@WebServlet(name = "TeacherRegistration")
/**
 * Lecturer registration
 */
public class TeacherRegistration extends HttpServlet {
    Models.Modules Modules = new Modules();

    /**
     * Bean Connection
     */
    @EJB
    TeacherRegistrationBean TeacherRegistrationBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // make teacher using jsp information
        Document teacher= new Document()
                .append("First_Name", request.getParameter("firstname"))
                .append("Last_Name", request.getParameter("lastname"))
                .append(request.getParameter("selection1"), Modules.getModule(request.getParameter("selection1")))
                .append(request.getParameter("selection2"), Modules.getModule(request.getParameter("selection2")))
                .append(request.getParameter("selection3"), Modules.getModule(request.getParameter("selection3")))
                .append(request.getParameter("selection4"), Modules.getModule(request.getParameter("selection4")))
                .append(request.getParameter("selection5"), Modules.getModule(request.getParameter("selection5")))
                .append("Email", request.getParameter("email"))
                .append("Password", request.getParameter("password"));

        // create teacher in the db
        TeacherRegistrationBean.createTeacher(teacher);

        FindIterable<Document> colTeacher = TeacherRegistrationBean.getTeacherList();

        MongoCursor<Document> cursor = colTeacher.iterator();

        try{
            // redirect user to login page
            response.sendRedirect("/login.jsp");
        } finally {
            cursor.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
