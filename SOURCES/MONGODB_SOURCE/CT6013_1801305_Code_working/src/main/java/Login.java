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

@WebServlet(name = "Login")
/**
 * Login user
 */
public class Login extends HttpServlet {

    /**
     * Bean Connection
     */
    @EJB
    LoginBean LoginBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // get values from jsp
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String typePerson = request.getParameter("selection1");

        try {
            // login check and send to jsp based no user type
            if (LoginBean.loginCheck(email, password, typePerson).equals(true)){
                if(typePerson.equals("teacher")){
                    response.sendRedirect("/teacherView.jsp");
                } else{
                    response.sendRedirect("/student_home.jsp");
                }
            } else {
                out.print("Not a user ....");
            }
        } catch (Exception ex) {
            out.print("Not a user ....");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
