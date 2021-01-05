import Models.TeacherModel;
import Models.TeacherModulesModel;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TeacherRegistrationOracle")
/**
 * Teacher Registration
 */
public class TeacherRegistrationOracle extends HttpServlet {
    /**
     * Connections to beans
     */
    @EJB
    TeacherRegistrationOracleBean TeacherRegistrationOracleBean;
    @EJB
    RegistrationOracleBean RegistrationOracleBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createTeacherUsingOracle(request, response);
    }

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    private void createTeacherUsingOracle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // create lecturer
        TeacherModel teacher = new TeacherModel();

        // set lecturer information
        teacher.setFirstName(request.getParameter("firstname"));
        teacher.setLastName(request.getParameter("lastname"));
        teacher.setEmail(request.getParameter("email"));
        teacher.setPassword(request.getParameter("password"));

        // check if they exist
        if (TeacherRegistrationOracleBean.getTeacherCheck(teacher.getEmail(), teacher.getPassword()).equals(true)){
            out.println("-------------------Teacher Already Exists------------------");
            return;
        }

        // create lecturer in the db
        TeacherRegistrationOracleBean.createTeacher(teacher);

        // get the lecturer id
        Integer teacherID = TeacherRegistrationOracleBean.getTeacherID(request.getParameter("email"));

        // create lecturer module
        TeacherModulesModel teacherModule1 = new TeacherModulesModel();
        // get the module id
        Integer moduleID1 = RegistrationOracleBean.getModuleID(request.getParameter("selection1"));

        // set the module information for lecturer
        teacherModule1.setTeacherID(teacherID);
        teacherModule1.setModuleID(moduleID1);

        // create lecturer module
        TeacherRegistrationOracleBean.createTeacherModules(teacherModule1);

        // create lecturer module
        TeacherModulesModel teacherModule2 = new TeacherModulesModel();

        // get the module id
        Integer moduleID2 = RegistrationOracleBean.getModuleID(request.getParameter("selection2"));

        // set the module information for lecturer
        teacherModule2.setTeacherID(teacherID);
        teacherModule2.setModuleID(moduleID2);

        // create lecturer module
        TeacherRegistrationOracleBean.createTeacherModules(teacherModule2);

        // create lecturer module
        TeacherModulesModel teacherModule3 = new TeacherModulesModel();

        // get the module id
        Integer moduleID3 = RegistrationOracleBean.getModuleID(request.getParameter("selection3"));

        // set the module information for lecturer
        teacherModule3.setTeacherID(teacherID);
        teacherModule3.setModuleID(moduleID3);

        // create lecturer module
        TeacherRegistrationOracleBean.createTeacherModules(teacherModule3);

        // create lecturer module
        TeacherModulesModel teacherModule4 = new TeacherModulesModel();

        // get the module id
        Integer moduleID4 = RegistrationOracleBean.getModuleID(request.getParameter("selection4"));

        // set the module information for lecturer
        teacherModule4.setTeacherID(teacherID);
        teacherModule4.setModuleID(moduleID4);

        // create lecturer module
        TeacherRegistrationOracleBean.createTeacherModules(teacherModule4);

        // create lecturer module
        TeacherModulesModel teacherModule5 = new TeacherModulesModel();

        // get the module id
        Integer moduleID5 = RegistrationOracleBean.getModuleID(request.getParameter("selection5"));

        // set the module information for lecturer
        teacherModule5.setTeacherID(teacherID);
        teacherModule5.setModuleID(moduleID5);

        // create lecturer module
        TeacherRegistrationOracleBean.createTeacherModules(teacherModule5);

        // sent to login jsp once completing registration
        response.sendRedirect("/login.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
