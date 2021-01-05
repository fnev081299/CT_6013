import Models.Modules;
import org.bson.Document;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Registration")
/**
 * Student Registration
 */
public class Registration extends HttpServlet {
    Models.Modules Modules = new Modules();

    /**
     * Bean Connection
     */
    @EJB
    RegistrationBean registrationBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int notInYear = 0;

        // values from jsp
        String Course_Code1 = request.getParameter("selection1");
        String Course_Code2 = request.getParameter("selection2");
        String Course_Code3 = request.getParameter("selection3");
        String Course_Code4 = request.getParameter("selection4");
        String Course_Code5 = request.getParameter("selection5");
        String year_group = request.getParameter("year_group");

        // create student document
        Document student= new Document()
                .append("First_Name", request.getParameter("firstname"))
                .append("Last_Name", request.getParameter("lastname"))
                .append("Student_ID", request.getParameter("student_id"))
                .append("Year_Group", request.getParameter("year_group"))
                .append("Email", request.getParameter("email"))
                .append("Password", request.getParameter("password"));

        // check module and year group
        if (registrationBean.yearCheck(year_group, Course_Code1).equals(true)){
            // make module document
            Document module1 = new Document()
            .append("Course_Name", Modules.getModule(request.getParameter("selection1")))
            .append("Grade_1", "-")
            .append("Grade_2", "-");
            student.put(Course_Code1, module1);

        } else if (registrationBean.yearCheck(year_group, Course_Code1).equals(false)){
            notInYear  += 1;
        }

        if (registrationBean.yearCheck(year_group, Course_Code2).equals(true)){
            // make module document
            Document module2 = new Document()
                    .append("Course_Name", Modules.getModule(request.getParameter("selection2")))
                    .append("Grade_1", "-")
                    .append("Grade_2", "-");
            student.put(Course_Code2, module2);
        } else if (registrationBean.yearCheck(year_group, Course_Code1).equals(false)){
            notInYear  += 1;
        }

        if (registrationBean.yearCheck(year_group, Course_Code3).equals(true)) {
            // make module document
            Document module3 = new Document()
                    .append("Course_Name", Modules.getModule(request.getParameter("selection3")))
                    .append("Grade_1", "-")
                    .append("Grade_2", "-");
            student.put(Course_Code3, module3);
        } else if (registrationBean.yearCheck(year_group, Course_Code1).equals(false)){
            notInYear  += 1;
        }

        if (registrationBean.yearCheck(year_group, Course_Code4).equals(true)) {
            // make module document
            Document module4 = new Document()
                    .append("Course_Name", Modules.getModule(request.getParameter("selection4")))
                    .append("Grade_1", "-")
                    .append("Grade_2", "-");
            student.put(Course_Code4, module4);
        } else if (registrationBean.yearCheck(year_group, Course_Code1).equals(false)){
            notInYear  += 1;
        }

        if (registrationBean.yearCheck(year_group, Course_Code5).equals(true)) {
            // make module document
            Document module5 = new Document()
                    .append("Course_Name", Modules.getModule(request.getParameter("selection5")))
                    .append("Grade_1", "-")
                    .append("Grade_2", "-");
            student.put(Course_Code5, module5);
        } else if (registrationBean.yearCheck(year_group, Course_Code1).equals(false)){
            notInYear  += 1;
        }

        try{

        } finally {
            // check if the user is not in the year group for one of the
            // modules and send to the login page if registration is successful
            if(notInYear >= 1) {
                out.println("The modules chosen are not accessible to this student...");
            } else {
                registrationBean.createStudent(student);
                response.sendRedirect("/login.jsp");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
