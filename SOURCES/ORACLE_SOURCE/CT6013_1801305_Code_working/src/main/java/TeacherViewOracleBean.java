import Models.StudentGradeModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;

@Stateless(name = "TeacherViewOracleEJB")
/**
 * Teacher view for the modules
 */
public class TeacherViewOracleBean {
    /**
     * Connection to db and beand needed
     */
    @EJB
    OracleClientProvideBean oracleClientProvideBean;
    @EJB
    GradeUpdateOracleBean gradeUpdateOracleBean;
    @EJB
    StudentHomeOracleBean studentHomeOracleBean;
    public TeacherViewOracleBean() {
    }

    /**
     * Get the student grades
     * @param teacherEmail lecturer email
     * @param module module
     * @return student grades list
     */
    public ArrayList<StudentGradeModel> getStudentsAndGrades(String teacherEmail, String module) {
        // get the students and put them in a list of students with the grades
        Boolean inLesson = gradeUpdateOracleBean.getLessonTeacher(teacherEmail, module);
        // check if the lecturer has access to module
        if (inLesson.equals(true)) {
            // select all from the module results table
            String query = "SELECT * FROM tblModuleResults WHERE Module_ID = ?";
            ArrayList student = new ArrayList();

            Statement stmt = null;
            try {
                // execute query
                Connection con = oracleClientProvideBean.getOracleClient();
                stmt = con.createStatement();
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, gradeUpdateOracleBean.getModuleID(module));
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()){
                    // create new student grade object and set values from db
                    StudentGradeModel studentGrade = new StudentGradeModel();
                    studentGrade.setStudent_Number(getStudentNumber(Integer.parseInt(rs.getString("Student_ID"))));
                    studentGrade.setModule_Code(module);
                    studentGrade.setModuleName(studentHomeOracleBean.getModuleName(gradeUpdateOracleBean.getModuleID(module)));
                    studentGrade.setGrade1(rs.getInt("Grade1"));
                    studentGrade.setGrade2(rs.getInt("Grade2"));

                    // add to the list of students
                    student.add(studentGrade);
                }
                stmt.close();

                // return the list of students
                return student;

            } catch (SQLException throwables) {
                // catch sql errors
                throwables.printStackTrace();
            }
            return null;
        } else {
            // not in lesson
            System.out.println("__--Not in Lesson Error--__");
            return null;
        }
    }

    /**
     * Get the student number from the student id
     * @param studentID student id
     * @return student number
     */
    public String getStudentNumber(Integer studentID){
        // get the number from the student table where the id matches
        String query = "SELECT Student_Number FROM tblStudents WHERE"
                + " Student_ID = ?";

        String student_Number = null;
        Statement stmt = null;

        try {
            // execute query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, studentID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                // get the student number
                student_Number = rs.getString("Student_Number");
            }

            stmt.close();

            // return the student number
            return student_Number;

        } catch (SQLException throwables) {
            // catch sql errors
            throwables.printStackTrace();
        }
        return null;
    }
}
