import Models.StudentModel;
import Models.StudentModulesModel;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StudentRegistrationOracle")
/**
 * Student Registration
 */
public class RegistrationOracle extends HttpServlet {
    /**
     * Connection to DB
     */
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
        createStudentUsingOracle(request, response);
    }

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    private void createStudentUsingOracle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // create student model
        StudentModel student = new StudentModel();

        // set student info from user input
        student.setStudent_Number(request.getParameter("student_number"));
        student.setFirstName(request.getParameter("firstname"));
        student.setLastName(request.getParameter("lastname"));
        student.setEmail(request.getParameter("email"));
        student.setPassword(request.getParameter("password"));
        student.setYearGroup(new Integer(request.getParameter("year_group")));

        // check user doesnt exist
        if (registrationOracleBean.getStudentCheck(student.getStudent_Number(), student.getEmail()).equals(true)){
            out.println("-------------------Student Already Exists------------------");
            return;
        }

        // check year group for module
        if(!registrationOracleBean.yearCheck(Integer.parseInt(request.getParameter("year_group")),
                registrationOracleBean.getModuleID(request.getParameter("selection1")))){
            out.println("Sorry but the modules selected are not for the year group chosen....");
            return;
        }
        // check year group for module
        if(!registrationOracleBean.yearCheck(Integer.parseInt(request.getParameter("year_group")),
                registrationOracleBean.getModuleID(request.getParameter("selection2")))){
            out.println("Sorry but the modules selected are not for the year group chosen....");
            return;
        }
        // check year group for module
        if(!registrationOracleBean.yearCheck(Integer.parseInt(request.getParameter("year_group")),
                registrationOracleBean.getModuleID(request.getParameter("selection3")))){
            out.println("Sorry but the modules selected are not for the year group chosen....");
            return;
        }
        // check year group for module
        if(!registrationOracleBean.yearCheck(Integer.parseInt(request.getParameter("year_group")),
                registrationOracleBean.getModuleID(request.getParameter("selection4")))){
            out.println("Sorry but the modules selected are not for the year group chosen....");
            return;
        }
        // check year group for module
        if(!registrationOracleBean.yearCheck(Integer.parseInt(request.getParameter("year_group")),
                registrationOracleBean.getModuleID(request.getParameter("selection5")))){
            out.println("Sorry but the modules selected are not for the year group chosen....");
            return;
        }

        // create student in db
        registrationOracleBean.createStudent(student);

        // get student id
        Integer studentID = registrationOracleBean.getStudentID(request.getParameter("student_number"));

        // This adds modules and students grades to the db
        StudentModulesModel studentModule1 = new StudentModulesModel();

        // module id
        Integer moduleID1 = registrationOracleBean.getModuleID(request.getParameter("selection1"));

        // set module information for this module
        studentModule1.setStudentID(studentID);
        studentModule1.setModuleID(moduleID1);
        studentModule1.setGrade1(0);
        studentModule1.setGrade2(0);

        // create student module model
        registrationOracleBean.createStudentModules(studentModule1);

        // This adds modules and students grades to the db
        StudentModulesModel studentModule2 = new StudentModulesModel();

        // module id
        Integer moduleID2 = registrationOracleBean.getModuleID(request.getParameter("selection2"));

        // set module information for this module
        studentModule2.setStudentID(studentID);
        studentModule2.setModuleID(moduleID2);
        studentModule2.setGrade1(0);
        studentModule2.setGrade2(0);

        // create student module model
        registrationOracleBean.createStudentModules(studentModule2);

        // This adds modules and students grades to the db
        StudentModulesModel studentModule3 = new StudentModulesModel();

        // module id
        Integer moduleID3 = registrationOracleBean.getModuleID(request.getParameter("selection3"));

        // set module information for this module
        studentModule3.setStudentID(studentID);
        studentModule3.setModuleID(moduleID3);
        studentModule3.setGrade1(0);
        studentModule3.setGrade2(0);

        // create student module model
        registrationOracleBean.createStudentModules(studentModule3);

        // This adds modules and students grades to the db
        StudentModulesModel studentModule4 = new StudentModulesModel();

        // module id
        Integer moduleID4 = registrationOracleBean.getModuleID(request.getParameter("selection4"));

        // set module information for this module
        studentModule4.setStudentID(studentID);
        studentModule4.setModuleID(moduleID4);
        studentModule4.setGrade1(0);
        studentModule4.setGrade2(0);

        // create student module model
        registrationOracleBean.createStudentModules(studentModule4);

        // This adds modules and students grades to the db
        StudentModulesModel studentModule5 = new StudentModulesModel();

        // module id
        Integer moduleID5 = registrationOracleBean.getModuleID(request.getParameter("selection5"));

        // set module information for this module
        studentModule5.setStudentID(studentID);
        studentModule5.setModuleID(moduleID5);
        studentModule5.setGrade1(0);
        studentModule5.setGrade2(0);

        // create student module model
        registrationOracleBean.createStudentModules(studentModule5);

        // redirect to login page
        response.sendRedirect("/login.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
