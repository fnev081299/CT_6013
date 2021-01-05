import Models.ModuleModel;
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

@WebServlet(name = "TeacherViewClassOracle")
/**
 * View of the classes and passes
 */
public class TeacherViewClassOracle extends HttpServlet {
    /**
     * Set the log in static object
     */
    LoggedIn loggedin = new LoggedIn();

    /**
     * Bean connections
     */
    @EJB
    TeacherViewClassOracleBean teacherViewClassOracleBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modulePassesView(request,response);
    }

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    private void modulePassesView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // check if logged in
        if (loggedin.getLogAuth()) {
            // create module model
            ModuleModel modulePasses = new ModuleModel();

            // make a list of passes
            ArrayList<ModuleModel> module_passes = teacherViewClassOracleBean.ModulePasses();

            Iterator<ModuleModel> cursor = module_passes.iterator();

            // list of passes in module
            ArrayList<ModuleModel> passesMod = new ArrayList<>();

            try {
                while (cursor.hasNext()) {
                    modulePasses = (ModuleModel) cursor.next();

                    // create module object
                    ModuleModel moduleObj = new ModuleModel();

                    // set the variables for object
                    moduleObj.setModule_Code(modulePasses.getModule_Code());
                    moduleObj.setPasses(modulePasses.getPasses());

                    // add to the passes list
                    passesMod.add(moduleObj);
                }

                // set the passes attribute
                request.setAttribute("passes", passesMod);

            } finally {
                // send the requests to the jsp
                request.getRequestDispatcher("teacherViewClass.jsp").forward(request, response);
            }
        } else {
            // logged out
            out.println("You are not logged in.....");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
