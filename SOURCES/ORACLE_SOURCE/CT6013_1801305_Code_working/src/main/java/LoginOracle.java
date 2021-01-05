import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginOracle")
/**
 * Logging in users
 */
public class LoginOracle extends HttpServlet {
    /**
     * Create login object
     */
    LoggedIn loggedin = new LoggedIn();

    /**
     * required beans
     */
    @EJB
    LoginOracleBean LoginOracleBean;
    @EJB
    TeacherRegistrationOracleBean teacherRegistrationOracleBean;
    @EJB
    RegistrationOracleBean registrationOracleBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Login(request, response);
    }

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // set the login to false
        loggedin.setfalse();

        PrintWriter out = response.getWriter();

        // gather info from user
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String typePerson = request.getParameter("selection1");

        // check the login
        if (LoginOracleBean.loginCheck(email, password, typePerson).equals(true)){

            // if lecturer then send them to lecturer home and set logged in to true
            if(typePerson.equals("teacher")){
                loggedin.setTrue();
                response.sendRedirect("/teacherView.jsp");
            } else{
                // if student then send them to student home and set logged in to true
                loggedin.setTrue();

                response.sendRedirect("/student_home.jsp");
            }
        } else {
            // not a user
            out.print("Not a user ....");
        }
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
