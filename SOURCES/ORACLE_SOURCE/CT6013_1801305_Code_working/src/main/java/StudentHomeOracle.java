import Models.StudentGradeModel;
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

@WebServlet(name = "StudentHomeOracle")
/**
 * Student home
 */
public class StudentHomeOracle extends HttpServlet {
    /**
     * Login object
     */
    LoggedIn loggedin = new LoggedIn();

    /**
     * Connection beans
     */
    @EJB
    StudentHomeOracleBean studentHomeOracleBean;
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
        transcriptOracle(request, response);
    }

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    private void transcriptOracle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // check if logged in
        if (loggedin.getLogAuth()) {
            // make student
            StudentModel student = new StudentModel();
            StudentGradeModel studentGrade = new StudentGradeModel();

            // set student number
            student.setStudent_Number(request.getParameter("student_number"));

            // get the student id
            Integer studentID = registrationOracleBean.getStudentID(student.getStudent_Number());

            // calls to get array
            ArrayList<StudentGradeModel> student_list = studentHomeOracleBean.getStudentGradeList(studentID);

            Iterator<StudentGradeModel> cursor = student_list.iterator();

            // set the student overall average
            student.setAvgGrade(studentHomeOracleBean.calcGrade(student_list));

            // set student attribute
            request.setAttribute("student",student);

            // make grades list
            ArrayList<StudentGradeModel> grades = new ArrayList<>();

            try {
                while (cursor.hasNext()) {
                    // set the grades for modules
                    studentGrade = (StudentGradeModel) cursor.next();
                    StudentGradeModel gradeObj = new StudentGradeModel();
                    gradeObj.setModuleName(studentGrade.getModuleName());
                    gradeObj.setModule_Code(studentGrade.getModule_Code());
                    gradeObj.setGrade1(studentGrade.getGrade1());
                    gradeObj.setGrade2(studentGrade.getGrade2());

                    // add the object to the grades list
                    grades.add(gradeObj);
                }

                // set the grades list attribute
                request.setAttribute("grades", grades);
            } finally {
                //cursor.close();
                // sent the requests to jsp
                request.getRequestDispatcher("student_home.jsp").forward(request, response);
            }
        } else {
            // not logged in
            out.println("You are not logged in.....");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}