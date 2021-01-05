import Models.StudentModel;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet(name = "GradeUpdateOracle")
/**
 * updates the student grades
 */
public class GradeUpdateOracle extends HttpServlet {
    /**
     * Check the user is login
     */
    LoggedIn loggedin = new LoggedIn();

    /**
     * Connect to the beans necessary
     */
    @EJB
    GradeUpdateOracleBean GradeUpdateOracleBean;
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
        UpdateGrade(request, response);
    }

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    private void UpdateGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // printer for possible errors
        PrintWriter out = response.getWriter();

        // check login
        if (loggedin.getLogAuth()){

            // student creation
            StudentModel student = new StudentModel();

            // setting values
            String Teacher_Email = request.getParameter("teacher_email");
            String Student_Number = request.getParameter("student_id");
            String Module = request.getParameter("selection1");
            String Student_Grade1 = request.getParameter("grade1");
            String Student_Grade2 = request.getParameter("grade2");

            // using the values to update the grades
            GradeUpdateOracleBean.updateGrade(Student_Number, Student_Grade1, Student_Grade2, Module, Teacher_Email);

            // iterating through values
            ArrayList<StudentModel> student_list = registrationOracleBean.getStudentList();
            Iterator<StudentModel> cursor = student_list.iterator();

            try {
                // redirect home
                response.sendRedirect("/teacherView.jsp");
            } finally {
                //cursor.close();
            }
        } else {
            // not logged in error
            out.println("You are not logged in.....");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
