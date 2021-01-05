import Models.TeacherModel;
import Models.TeacherModulesModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;

@Stateless(name = "TeacherRegistrationOracleEJB")
/**
 * Teacher Registration
 */
public class TeacherRegistrationOracleBean {
    /**
     * Db connection
     */
    @EJB
    OracleClientProvideBean oracleClientProvideBean;
    public TeacherRegistrationOracleBean() {
    }

    /**
     * Creating the lecturer
     * @param teacherModel teacher model
     */
    public void createTeacher(TeacherModel teacherModel) {
        // inserting the lecturer into the db
        String insertTeacher = "INSERT INTO tblLecturers"
                + "(Lecturer_ID, FirstName, LastName, Email, Password)" + "VALUES ("
                + "lecturer_id_seq.nextval, "
                + " '" + teacherModel.getFirstName() + "', "
                + " '" + teacherModel.getLastName() + "', "
                + " '" + teacherModel.getEmail() + "', "
                + " '" + teacherModel.getPassword() + "')";

        Statement stmt = null;
        Connection con = oracleClientProvideBean.getOracleClient();
        try{
            // execute insert SQL statement
            stmt = con.createStatement();
            System.out.println(insertTeacher);
            stmt.executeUpdate(insertTeacher);
            con.commit();
            stmt.close();
            con.close();

        } catch (SQLException throwables) {
            // catches exceptions and ROLLBACK if needed
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
     * Setting the lecturer modules in the DB
     * @param teacherModule teacher module
     */
    public void createTeacherModules(TeacherModulesModel teacherModule){
        // insert into module lecturer table the lecture module connections
        String insertTeacher = "INSERT INTO tblModuleLecturers"
                + "(Module_Lecturers_ID, Module_ID, Lecturer_ID)" + "VALUES ("
                + "Module_Lecturers_id_seq.nextval, "
                + " " + teacherModule.getModuleID() + ", "
                + " " + teacherModule.getTeacherID() + ")";

        Statement stmt = null;
        Connection con = oracleClientProvideBean.getOracleClient();

        try{
            // execute insert SQL statement
            stmt = con.createStatement();
            System.out.println(insertTeacher);
            stmt.executeUpdate(insertTeacher);
            con.commit();
            stmt.close();
            con.close();

        } catch (SQLException throwables) {
            // catches exceptions and ROLLBACK if needed
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
     * Get the id form the email
     * @param email email
     * @return teacher id
     */
    public Integer getTeacherID(String email){
        // query for retrieving the id
        String query = "SELECT Lecturer_ID FROM tblLecturers WHERE"
                + " Email = ?";

        Integer teacherID = null;
        Statement stmt = null;

        try {
            // execute insert SQL statement
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                // get the id
                teacherID = Math.toIntExact(rs.getLong("Lecturer_ID"));
            }

            stmt.close();

            // return the id
            return teacherID;

        } catch (SQLException throwables) {
            // catches exceptions
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Gets a list of lecturers
     * @return list of lecturers
     */
    public ArrayList<TeacherModel> getTeacherList(){
        // selects the lecturers from the db
        String query = "SELECT * FROM tblLecturers";

        // make list of lecturers
        ArrayList Lecturer_list = new ArrayList();

        Statement stmt = null;
        try {
            // execute insert SQL statement
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                // make a lecturer and set their information
                TeacherModel teacher = new TeacherModel();
                teacher.setTeacher_ID(rs.getLong("Lecturer_ID"));
                teacher.setFirstName(rs.getString("FirstName"));
                teacher.setLastName(rs.getString("LastName"));
                teacher.setEmail(rs.getString("Email"));
                teacher.setPassword(rs.getString("Password"));

                // add lecturer to the list
                Lecturer_list.add(teacher);
            }
            stmt.close();

            // return the list
            return Lecturer_list;

        } catch (SQLException throwables) {
            // catches exceptions
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Check if the lecturer exists in the DB
     * @param email email
     * @param password password
     * @return if the lecturer exists
     */
    public Boolean getTeacherCheck(String email, String password){
        // get the password form the db
        String query = "SELECT Password FROM tblLecturers WHERE"
                + " Email = ?";

        Statement stmt = null;

        try {
            // execute insert SQL statement
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                String x = rs.getString("Password");

                // check if the password matches the db password
                if (x.equals(password)){
                    // return true there is a lecturer
                    return true;
                } else {
                    // return false no lecturer
                    return false;
                }
            }

            stmt.close();

        } catch (SQLException throwables) {
            // catches exceptions
            return false;
        }
        return false;
    }
}
