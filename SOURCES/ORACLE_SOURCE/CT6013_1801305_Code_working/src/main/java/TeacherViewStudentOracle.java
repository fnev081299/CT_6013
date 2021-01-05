import Models.StudentGradeModel;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TeacherViewStudentOracle")
/**
 * View specific student
 */
public class TeacherViewStudentOracle extends HttpServlet {
    /**
     * Login static variable
     */
    LoggedIn loggedin = new LoggedIn();

    /**
     * Bean connection
     */
    @EJB
    TeacherViewStudentOracleBean teacherViewStudentOracleBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        moduleStudentView(request,response);
    }

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    private void moduleStudentView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // gets the email of teacher, student ID and the module number to get the student information
        // check that the teacher is on module, if so return all students on that module
        PrintWriter out = response.getWriter();

        // check logged in
        if (loggedin.getLogAuth()) {

            // get lecturer info
            String Teacher_Email = request.getParameter("teacher_email");
            String Module = request.getParameter("selection1");
            String Student_Number = request.getParameter("student_number");


            try {
                // get student info
                StudentGradeModel student_info = teacherViewStudentOracleBean.getStudentInfo(Teacher_Email, Module, Student_Number);

                try {
                    // set the student info as attributes for the jsp
                    request.setAttribute("studentNumber",student_info.getStudent_Number());
                    request.setAttribute("module",student_info.getModuleName());
                    request.setAttribute("courseCode",student_info.getModule_Code());
                    request.setAttribute("grade1",student_info.getGrade1() );
                    request.setAttribute("grade2",student_info.getGrade2());

                } finally {
                    //cursor.close();
                    // give attributes to jsp
                    request.getRequestDispatcher("teacherViewSpecific.jsp").forward(request, response);
                }
            } catch (NullPointerException ex) {
                // no lesson or lecturer cannot access module
                out.println("Not in lesson or teacher cannot access module.....");
            }
        } else {
            // logged out
            out.println("You are not logged in .....");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
