import javafx.stage.StageStyle;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Stateless(name = "LoginoEJB")
/**
 * Login user bean
 */
public class LoginOracleBean {
    /**
     * DB connection bean
     */
    @EJB
    OracleClientProvideBean oracleClientProvideBean;
    public LoginOracleBean() {}

    /**
     * Check login details are correct
     * @param email email
     * @param password password
     * @param typePerson user type
     * @return
     */
    public Boolean loginCheck(String email, String password, String typePerson){
        // if the person is a lecturer
        if (typePerson.equals("teacher")){
            // if the lecturer login is correct
            if (checkTeacherLogin(email, password).equals(true)){
                // return true for login
                return true;
            } else{
                // return false for no login
                return false;
            }
        } else if (typePerson.equals("student")){
            // if the student login is correct
            if (checkStudentLogin(email, password).equals(true)){
                // return true for login
                return true;
            } else{
                // return false for no login
                return false;
            }
        }
        return null;
    }

    /**
     * Check the lecturer information is correct
     * @param email email
     * @param password password
     * @return if correct info entered
     */
    public Boolean checkTeacherLogin(String email, String password){

        // get the password from the email in the DB table
        String query = "SELECT Password FROM tblLecturers WHERE"
                + " Email = '" + email + "'";

        String truePass = null;
        Statement stmt = null;

        try {
            // execute query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                truePass = rs.getString("Password");

                // check password with DB password
                if (password.equals(truePass)){
                    return true;
                } else{
                    return false;
                }
            }

            stmt.close();

        } catch (SQLException throwables) {
            //SQL exceptions
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * Check the student information is correct
     * @param email email
     * @param password password
     * @return if correct info entered
     */
    public Boolean checkStudentLogin(String email, String password){
        // get the password from the email in the DB table
        String query = "SELECT Password FROM tblStudents WHERE"
                + " Email = '" + email + "'";

        String truePass = null;
        Statement stmt = null;

        try {
            // execute query
            Connection con = oracleClientProvideBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                truePass = rs.getString("Password");
                // check password with DB password
                if (password.equals(truePass)){
                    return true;
                } else{
                    return false;
                }
            }

            stmt.close();

        } catch (SQLException throwables) {
            //SQL exceptions
            throwables.printStackTrace();
        }
        return false;
    }

}
