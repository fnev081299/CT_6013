import Models.StudentGradeModel;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import com.mongodb.client.MongoCursor;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "TeacherView")
/**
 * Teacher view
 */
public class TeacherView extends HttpServlet {

    /**
     * Bean Connection
     */
    @EJB
    TeacherViewBean TeacherViewBean;

    /**
     *
     * @param request Given requested values
     * @param response Returning values
     * @throws ServletException Any servlet errors
     * @throws IOException Any I/O errors
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // get the jsp user inputs
        String email = request.getParameter("teacher_email");
        String courseCode = request.getParameter("selection1");

        // set the module value
        request.setAttribute("module", courseCode);

        // list of the grades to return
        ArrayList<StudentGradeModel> grades = new ArrayList<>();

        try {
            FindIterable<Document> colAllStudent = TeacherViewBean.getAllStudents(email, courseCode);
            MongoCursor<Document> cursor = colAllStudent.iterator();

            try {
                // counter for the passes
                Integer counterPass = 0;

                while (cursor.hasNext()) {
                    Document doc = (Document) cursor.next();
                    StudentGradeModel gradeObj = new StudentGradeModel();

                    // set student id
                    gradeObj.setStudent_Number(doc.get("Student_ID").toString());

                    // statement to get the specific modules they are on to output
                    if (doc.containsKey(courseCode)) {
                        String gg1;
                        String gg2;

                        Document m = (Document) doc.get(courseCode);

                        // pass or fail
                        gg1 = m.get("Grade_1").toString();
                        gg2 = m.get("Grade_2").toString();

                        if (gg1.equals("-")) {
                            gg1 = "0";
                        }

                        if (gg2.equals("-")) {
                            gg2 = "0";
                        }

                        int g1 = Integer.parseInt(gg1);
                        int g2 = Integer.parseInt(gg2);

                        int average = (g1 / 2) + (g2 / 2);

                        if (g1 == 0 || g2 == 0) {
                            gradeObj.setGrade1(g1);
                            gradeObj.setGrade2(g2);
                        } else if (average > 40) {
                            // add to counter to output the total passes
                            gradeObj.setGrade1(g1);
                            gradeObj.setGrade2(g2);
                            // add to a module db about passing
                            counterPass += 1;
                        } else {
                            gradeObj.setGrade1(g1);
                            gradeObj.setGrade2(g2);
                        }

                        // add to grades list
                        grades.add(gradeObj);
                    }
                }
                // add module passes
                TeacherViewBean.addModulePasses(courseCode, counterPass);
                // set grade list attribute to return to jsp
                request.setAttribute("grades", grades);
            } finally {
                // set dispatcher to teacher view jsp
                request.getRequestDispatcher("teacherView.jsp").forward(request, response);
                //cursor.close();
            }
        } catch(NullPointerException e) {
                out.println("Sorry, you dont have access to this module....");
            }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
