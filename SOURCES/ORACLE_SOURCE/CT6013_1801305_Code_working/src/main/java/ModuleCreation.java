import Models.ModuleModel;

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

/**
 * NOT USED
 */
@WebServlet(name = "ModuleCreation")
public class ModuleCreation extends HttpServlet {
    @EJB
    ModuleCreationBean moduleCreationBean;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createModuleUsingOracle(request, response);
    }

    private void createModuleUsingOracle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        ModuleModel module = new ModuleModel();
        out.println("Processing Registration....");

        module.setModule_Code(request.getParameter("module_num"));
        module.setModule_Name(request.getParameter("module_name"));
        module.setYearGroup(new Integer(request.getParameter("selection1")));

        moduleCreationBean.createModule(module);

        out.println("Registration Completed...");

        ArrayList<ModuleModel> module_list = moduleCreationBean.getModuleList();
        Iterator<ModuleModel> cursor = module_list.iterator();

        out.println("Module Listing Below...");

        try {
            while(cursor.hasNext()){
                module = (ModuleModel)cursor.next();
                out.println("Module Number: " + module.getModule_Code());
                out.println("Module Name: " + module.getModule_Name());
                out.println("Year Group: " + module.getYearGroup());
                out.println("-------------------------------------");

            }
        } finally {
            //cursor.close();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
