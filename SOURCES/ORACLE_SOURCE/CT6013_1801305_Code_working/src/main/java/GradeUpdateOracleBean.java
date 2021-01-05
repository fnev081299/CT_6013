import Models.ModuleModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;

@Stateless(name = "GradeUpdateOracleEJB")
/**
 * Updating the student grades
 */
public class GradeUpdateOracleBean {

    /**
     * connect to DB
     */
    @EJB
    OracleClientProvideBean oracleClientProvideBean;

    public GradeUpdateOracleBean() {
    }

    /**
     * With these variables the update can check for the lecturer authentication to set the grades and then set them.
     * @param Student_Number student number
     * @param grade1 grade 1
     * @param grade2 grade 2
     * @param module module
     * @param teacherEmail teacher email
     */
    public void updateGrade(String Student_Number, String grade1, String grade2, String module, String teacherEmail) {
        // check if lecturer is in the module
        Boolean inLesson = getLessonTeacher(teacherEmail, module);

        // get the student id
        Integer student_ID = getStudentID(Student_Number);

        // if lecturer is in lesson update the grades
        if (inLesson.equals(true)){
            //update grade for student
            if (!grade1.equals("") || !grade1.isEmpty()){
                updatingGrade1(getModuleID(module), student_ID, Integer.parseInt(grade1));
            }

            //update grade for student
            if (!grade2.equals("")|| !grade2.isEmpty()){
                updatingGrade2(getModuleID(module), student_ID, Integer.parseInt(grade2));
            }
        }
    }

    /**
     * Check if the lecturer is in the lesson
     * @param teacherEmail teacher email
     * @param module module
     * @return if the lecturer is in the lesson to change a grade
     */
    public Boolean getLessonTeacher(String teacherEmail, String module){
        // get the teacher id
        Integer teacher_ID = getTeacherID(teacherEmail);

        // selecting the modules that the lecturer teaches
        String query = "SELECT Module_ID FROM tblModuleLecturers WHERE"
                + " Lecturer_ID = ?";

        Statement stmt = null;
        ArrayList moduleList = new ArrayList();

        // call to get the module ID
        Integer module_ID = getModuleID(module);

        try {

            // connect to the client
            Connection con = oracleClientProvideBean.getOracleClient();

            // execute the query
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, teacher_ID);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){

                //get the module id
                ModuleModel moduleItem = new ModuleModel();
                moduleItem.setModule_ID(rs.getLong("Module_ID"));
                Integer x = Integer.parseInt(rs.getString("Module_ID"));

                // does the teacher have the module id
                if (x == module_ID) {

                    // return that the lecturer is in the module
                    return true;
                }

                // add the module item to the module list
                moduleList.add(moduleItem);
            }

            stmt.close();

        } catch (SQLException throwables) {
            // catch the SQL errors
            throwables.printStackTrace();
            System.out.println("-- Error 1 ---");
        }
        return false;
    }

    /**
     * Updating the student grade 1
     * @param moduleID module id
     * @param studentID student id
     * @param grade grade
     */
    public void updatingGrade1(Integer moduleID, Integer studentID, Integer grade){

        // query for the updating in the DB
        String query = "UPDATE tblModuleResults "
                + "SET Grade1 = " + grade + " " +
                "WHERE Module_ID = " + moduleID + " " +
                "AND Student_ID = " + studentID + " ";

        Statement stmt = null;
        Connection con = oracleClientProvideBean.getOracleClient();

        try {
            // executing the update
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            con.commit();
            stmt.close();
            con.close();

        } catch (SQLException throwables) {
            // catch SQL errors and ROLLBACK
            throwables.printStackTrace();
            System.out.println("--- Grade 1 cannot updated!! Rollback  is being attempted----");
            try{
                if(con!=null)
                    con.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try
        }
    }

    /**
     * Updating the student grade 2
     * @param moduleID module id
     * @param studentID student id
     * @param grade grade
     */
    public void updatingGrade2(Integer moduleID, Integer studentID, Integer grade){

        // query for the updating in the DB
        String query = "UPDATE tblModuleResults "
                + "SET Grade2 = " + grade + " " +
                "WHERE Module_ID = " + moduleID + " " +
                "AND Student_ID = " + studentID + " ";

        Statement stmt = null;
        Connection con = oracleClientProvideBean.getOracleClient();
        try {
            // executing the update
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println(query);
            con.commit();
            stmt.close();
            con.close();

        } catch (SQLException throwables) {
            // catch SQL errors and ROLLBACK
            throwables.printStackTrace();
            System.out.println("--- Grade 2 cannot updated!! Rollback is being attempted----");
            try{
                if(con!=null)
                    con.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try
        }
    }

    /**
     * Gets the student id for other queries in functions
     * @param student_Number student number
     * @return the student id
     */
    public Integer getStudentID(String student_Number){

        // query for the selecting of the student id
        String query = "SELECT Student_ID FROM tblStudents WHERE"
                + " Student_Number = '" + student_Number + "'";

        Statement stmt = null;

        try {
            //executing the query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            Integer Student_ID = Integer.valueOf(rs.getString("Student_ID"));

            stmt.close();

            // return the id value
            return Student_ID;

        } catch (SQLException throwables) {

            // SQL error handling
            throwables.printStackTrace();
            System.out.println("Student ID return error");
        }

        return null;
    }

    /**
     * Get the teacher id from the email
     * @param teacherEmail lecturer email
     * @return lecturer id
     */
    public Integer getTeacherID(String teacherEmail) {

        // query for the lecturer id
        String query = "SELECT Lecturer_ID FROM tblLecturers WHERE"
                + " Email = '" + teacherEmail + "'";

        Statement stmt = null;

        try {
            // execute query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            Integer Teacher_ID = Integer.valueOf(rs.getString("Lecturer_ID"));
            stmt.close();

            // return id
            return Teacher_ID;

        } catch (SQLException throwables) {
            // catch SQL errors
            throwables.printStackTrace();
            System.out.println("Teacher ID return error");
        }
        return null;
    }

    /**
     * get the module id from the module code
     * @param module module
     * @return module id
     */
    public Integer getModuleID(String module) {

        // query for the module id
        String query = "SELECT Module_ID FROM tblModules WHERE"
                + " Module_Code = '" + module + "'";

        Statement stmt = null;

        try {

            // executing query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            Integer Module_ID = Integer.valueOf(rs.getString("Module_ID"));
            stmt.close();

            // returning the id
            return Module_ID;

        } catch (SQLException throwables) {
            // catching SQL errors
            throwables.printStackTrace();
            System.out.println("Module ID return error");
        }

        return null;
    }

}
