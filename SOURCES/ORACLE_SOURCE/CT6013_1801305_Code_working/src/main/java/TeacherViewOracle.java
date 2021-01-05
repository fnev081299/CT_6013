import Models.StudentGradeModel;

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

@WebServlet(name = "TeacherViewOracle")
/**
 * Teacher view for the modules
 */
public class TeacherViewOracle extends HttpServlet {

    /**
     * Login static variable
     */
    LoggedIn loggedin = new LoggedIn();

    /**
     * Bean connection
     */
    @EJB
    TeacherViewOracleBean teacherViewOracleBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        moduleView(request,response);
    }

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    private void moduleView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // gets the email of teacher and the module number to get the student information
        // check that the teacher is on module, if so return all students on that module
        PrintWriter out = response.getWriter();

        // check if logged in
        if (loggedin.getLogAuth()) {

            // student grade model create
            StudentGradeModel studentGrades = new StudentGradeModel();

            // get teh teacher email and module
            String Teacher_Email = request.getParameter("teacher_email");
            String Module = request.getParameter("selection1");

            // get the student grades from bean
            ArrayList<StudentGradeModel> student_list = teacherViewOracleBean.getStudentsAndGrades(Teacher_Email, Module);
            Iterator<StudentGradeModel> cursor = student_list.iterator();

            // list of grades
            ArrayList<StudentGradeModel> grades = new ArrayList<>();

            // set attribute for module object
            request.setAttribute("module", Module);

            try {
                while (cursor.hasNext()) {
                    // create student grade and add their grade information
                    studentGrades = (StudentGradeModel) cursor.next();
                    StudentGradeModel gradeObj = new StudentGradeModel();
                    gradeObj.setStudent_Number(studentGrades.getStudent_Number());
                    gradeObj.setGrade1(studentGrades.getGrade1());
                    gradeObj.setGrade2(studentGrades.getGrade2());

                    // add to the grades list
                    grades.add(gradeObj);

                }

                // set the attribute for the grades list
                request.setAttribute("grades", grades);

            } finally {
                //cursor.close();
                // send the requests to the lecturer view
                request.getRequestDispatcher("teacherView.jsp").forward(request, response);
            }
        } else {
            // logged out
            out.println("You are not logged in.....");
        }
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
