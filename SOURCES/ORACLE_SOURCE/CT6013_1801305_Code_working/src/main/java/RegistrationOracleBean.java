import Models.StudentModel;
import Models.StudentModulesModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;

@Stateless(name = "RegistrationOracleEJB")
/**
 * Student Registration
 */
public class RegistrationOracleBean {
    /**
     * Connection to DB
     */
    @EJB
    OracleClientProvideBean oracleClientProvideBean;
    public RegistrationOracleBean() {
    }

    /**
     * Creates a student in the DB
     * @param studentModel student model
     */
    public void createStudent(StudentModel studentModel) {
        // insert query for student creation
        String insertStudent = "INSERT INTO tblStudents"
                + "(Student_ID, Student_Number, FirstName, LastName, Email, Password, YearGroup)" + "VALUES ("
                + "student_id_seq.nextval, "
                + " '" + studentModel.getStudent_Number() + "', "
                + " '" + studentModel.getFirstName() + "', "
                + " '" + studentModel.getLastName() + "', "
                + " '" + studentModel.getEmail() + "', "
                + " '" + studentModel.getPassword() + "', "
                + " '" + studentModel.getYearGroup() + "')";

        Statement stmt = null;
        Connection con = oracleClientProvideBean.getOracleClient();

        try{
            // execute insert SQL statement
            stmt = con.createStatement();
            System.out.println(insertStudent);
            stmt.executeUpdate(insertStudent);
            con.commit();
            stmt.close();
            con.close();

        } catch (SQLException throwables) {
            // exceptions and ROLLBACK if needed
            throwables.printStackTrace();
            try{
                if(con!=null)
                    con.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try
        }
    }

    /**
     * Creating the student module inserting the grades and student information
     * @param studentModule student module
     */
    public void createStudentModules(StudentModulesModel studentModule){
        // query for inserting to db
        String insertStudent = "INSERT INTO tblModuleResults"
                + "(Module_Result_ID, Module_ID, Student_ID, Grade1, Grade2)" + "VALUES ("
                + "Module_Result_id_seq.nextval, "
                + studentModule.getModuleID() + ", "
                + " " + studentModule.getStudentID() + ", "
                + " " + studentModule.getGrade1() + ", "
                + " " + studentModule.getGrade2() + ")";

        Statement stmt = null;
        Connection con = oracleClientProvideBean.getOracleClient();
        try{
            // execute insert SQL statement
            stmt = con.createStatement();
            System.out.println(insertStudent);
            stmt.executeUpdate(insertStudent);
            con.commit();
            stmt.close();
            con.close();

        } catch (SQLException throwables) {
            // exceptions and ROLLBACK if needed
            throwables.printStackTrace();
            try{
                if(con!=null)
                    con.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try
        }
    }

    /**
     * Get a list of student models back
     * @return student model
     */
    public ArrayList<StudentModel> getStudentList(){
        // selecting from db query
        String query = "SELECT * fROM tblStudents";
        ArrayList student_list = new ArrayList();

        Statement stmt = null;
        try {
            // execute query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                // create the student
                StudentModel student = new StudentModel();
                student.setStudent_ID(rs.getLong("Student_ID"));
                student.setStudent_Number(rs.getString("Student_Number"));
                student.setFirstName(rs.getString("FirstName"));
                student.setLastName(rs.getString("LastName"));
                student.setEmail(rs.getString("Email"));
                student.setPassword(rs.getString("Password"));
                student.setYearGroup(rs.getInt("YearGroup"));

                // add student to the student list
                student_list.add(student);
            }
            stmt.close();

            return student_list;

        } catch (SQLException throwables) {
            // SQL exceptions
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the student id
     * @param student_Number student number
     * @return
     */
    public Integer getStudentID(String student_Number){
        // selects student id from db
        String query = "SELECT Student_ID FROM tblStudents WHERE"
                + " Student_Number = ?";

        Integer studentID = null;
        Statement stmt = null;
        Connection con = oracleClientProvideBean.getOracleClient();

        try {
            // execute query
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, student_Number);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                // set the id variable
                studentID = Math.toIntExact(rs.getLong("Student_ID"));
            }

            stmt.close();

            // return id
            return studentID;

        } catch (SQLException throwables) {
            // SQL exceptions
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Get the id from the student email
     * @param email email
     * @return student id
     */
    public Integer getStudentIDByEmail(String email){
        // query to gather the id from db
        String query = "SELECT Student_ID FROM tblStudents WHERE"
                + " Email = ?";

        Integer studentID = null;
        Statement stmt = null;
        Connection con = oracleClientProvideBean.getOracleClient();

        try {
            // executing the query
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                // get the student id
                studentID = Math.toIntExact(rs.getLong("Student_ID"));
            }

            stmt.close();

            // return the id
            return studentID;

        } catch (SQLException throwables) {
            // SQL exceptions
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Get the module id from the module code
     * @param module_Code module code
     * @return module id
     */
    public Integer getModuleID(String module_Code){
        // query gathering the id from the db where the module code exists
        String query = "SELECT Module_ID FROM tblModules WHERE"
                + " Module_Code = ?";

        Integer moduleID = null;
        Statement stmt = null;

        try {
            // execute the query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, module_Code);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                // get the id
                moduleID = Math.toIntExact(rs.getLong("Module_ID"));
            }

            stmt.close();

            // return the id
            return moduleID;

        } catch (SQLException throwables) {
            // SQL exceptions
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Checks that the student is in the year group for the module chosen
     * @param studentYear year group
     * @param module_ID module id
     * @return if the student is in the year for module
     */
    public Boolean yearCheck(Integer studentYear, Integer module_ID){
        // query to gather the year group from the db
        String query = "SELECT YearGroup FROM tblModules WHERE Module_ID = ?";

        Integer moduleYear = null;
        Statement stmt = null;

        try {
            // executing the query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, module_ID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                // setting the year group
                moduleYear = rs.getInt("YearGroup");
            }

            stmt.close();

            // check if they match
            if(moduleYear.equals(studentYear)){
                // return that they are in the year
                return true;
            } else{
                // not in the year
                return false;
            }

        } catch (SQLException throwables) {
            // SQL exceptions
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * Checks if the student exists in the DB
     * @param student_Number student number
     * @param email email
     * @return if the student exists
     */
    public Boolean getStudentCheck(String student_Number, String email){
        // get the email from the db
        String query = "SELECT Email FROM tblStudents WHERE Student_Number = ?";
        Statement stmt = null;

        try {
            // execute query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, student_Number);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                // set x as the email
                String x = rs.getString("Email");

                // check if the emails match
                if (x.equals(email)){
                    // return if they match
                    return true;
                } else {
                    // they do not match
                    return false;
                }
            }

            stmt.close();

        } catch (SQLException throwables) {
            // SQL exceptions
            return false;
        }
        return false;
    }

}
