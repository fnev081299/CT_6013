import Models.ModuleModel;
import Models.StudentGradeModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;

@Stateless(name = "TeacherViewClassOracleEJB")
/**
 * View of the classes and passes
 */
public class TeacherViewClassOracleBean {
    /**
     * DB connection and needed beans
     */
    @EJB
    OracleClientProvideBean oracleClientProvideBean;

    @EJB
    TeacherViewOracleBean teacherViewOracleBean;

    public TeacherViewClassOracleBean() {
    }

    /**
     * Get a list of passes
     * @return list of passes
     */
    public ArrayList<ModuleModel> ModulePasses(){
        // selecting module coeds form the module table
        String query = "SELECT Module_Code FROM tblModules";

        // make lists for the module and student passes
        ArrayList<ModuleModel> modulePasses = new ArrayList();
        ArrayList<StudentGradeModel> studentPasses = StudentPasses();

        Statement stmt = null;
        try {
            // execute query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                // create module model and add values
                ModuleModel ModulePasses = new ModuleModel();
                ModulePasses.setModule_Code(rs.getString("Module_Code"));

                int passes = 0;
                // count the number of passes
                for (int i = 0; i < studentPasses.size(); i++){
                    if (studentPasses.get(i).getPass().equals(true) && studentPasses.get(i).getModule_Code().equals(ModulePasses.getModule_Code())){
                        passes += 1;
                    }
                }
                // set the overall passes
                ModulePasses.setPasses(passes);

                // add to the list
                modulePasses.add(ModulePasses);
            }

            stmt.close();

            // return the list
            return modulePasses;

        } catch (SQLException throwables) {
            // catch sql errors
            throwables.printStackTrace();
        }
        return null;

    }

    /**
     * Get the passes from students
     * @return Student passes
     */
    public ArrayList<StudentGradeModel> StudentPasses(){
        // return array
        String query = "SELECT * FROM tblModuleResults ";
        ArrayList<StudentGradeModel> student = new ArrayList();

        Statement stmt = null;
        try {
            // execute query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                // make student grade model and add values
                StudentGradeModel studentGrade = new StudentGradeModel();
                studentGrade.setModule_Code(getModuleCodeFromID(rs.getInt("Module_ID")));
                studentGrade.setStudent_Number(teacherViewOracleBean.getStudentNumber(rs.getInt("Student_ID")));
                studentGrade.setPass(checkPass(rs.getInt("Grade1"), rs.getInt("Grade2")));

                // add to student list
                student.add(studentGrade);
            }

            stmt.close();
            // return the list
            return student;

        } catch (SQLException throwables) {
            // catch sql errors
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Get the module code from the id
     * @param moduleID  module id
     * @return module code from id
     */
    public String getModuleCodeFromID(Integer moduleID){
        // select the module code from the table where the id matches
        String query = "SELECT Module_Code FROM tblModules WHERE Module_ID = ?";
        Statement stmt = null;

        try {
            // execute query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, moduleID);
            ResultSet rs = pstmt.executeQuery();
            String ModuleName = null;

            while (rs.next()){
                // get the code
                ModuleName = rs.getString("Module_Code");
            }
            stmt.close();

            // return the module code
            return ModuleName;

        } catch (SQLException throwables) {
            // catch sql errors
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if the grades entered equate to a pass
     * @param grade1 grade 1
     * @param grade2 grade 2
     * @return if the grades equal a pass
     */
    public Boolean checkPass(Integer grade1, Integer grade2){
        // get the average
        int average = (grade1 / 2) + (grade2 / 2);

        // check if it is less than 40
        if (grade1 == 0 || grade2 == 0) {
            // missing grade
            return false;
        } else if (average > 40) {
            // return true if they pass
            return true;
        } else {
            return false;
        }
    }
}
