import Models.StudentGradeModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.*;

@Stateless(name = "TeacherViewStudentOracleEJB")
/**
 * View specific student
 */
public class TeacherViewStudentOracleBean {
    /**
     * Connection to DB and needed beans
     */
    @EJB
    OracleClientProvideBean oracleClientProvideBean;
    @EJB
    GradeUpdateOracleBean gradeUpdateOracleBean;
    @EJB
    TeacherViewOracleBean teacherViewOracleBean;
    @EJB
    StudentHomeOracleBean studentHomeOracleBean;
    public TeacherViewStudentOracleBean() {
    }

    /**
     * Get the specific student grade model
     * @param teacherEmail lecturer email
     * @param module module
     * @param studentNumber student number
     * @return student grade model
     */
    public StudentGradeModel getStudentInfo(String teacherEmail, String module, String studentNumber){
        // check if lecturer has access to lesson
        Boolean inLesson = gradeUpdateOracleBean.getLessonTeacher(teacherEmail, module);
        // if access to the lesson do this
        if (inLesson.equals(true)){
            // get the student where the module id and student id match up
            String query = "SELECT * FROM tblModuleResults WHERE Module_ID = ? AND Student_ID = ?";

            Statement stmt = null;
            StudentGradeModel studentGrade = new StudentGradeModel();

            try {
                // execute query
                Connection con = oracleClientProvideBean.getOracleClient();
                stmt = con.createStatement();
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, gradeUpdateOracleBean.getModuleID(module));
                pstmt.setInt(2, gradeUpdateOracleBean.getStudentID(studentNumber));
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()){
                    // set the object attributes for the student grade model
                    studentGrade.setStudent_Number(studentNumber);
                    studentGrade.setModule_Code(module);
                    studentGrade.setModuleName(studentHomeOracleBean.getModuleName(gradeUpdateOracleBean.getModuleID(module)));
                    studentGrade.setGrade1(rs.getInt("Grade1"));
                    studentGrade.setGrade2(rs.getInt("Grade2"));
                }

                stmt.close();

                // return the object
                return studentGrade;

            } catch (SQLException throwables) {
                // catch possible sql errors
                throwables.printStackTrace();
            }
            return null;
        } else {
            // lecturer is not in lesson
            System.out.println("__--Not in Lesson Error--__");
            return null;
        }
    }

}
