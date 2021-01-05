import Models.StudentGradeModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Stateless  (name="StudentHomeOracleEJB")
/**
 * Student home
 */
public class StudentHomeOracleBean {
  /**
   * Connection to db
   */
  @EJB
  OracleClientProvideBean oracleClientProvideBean;
  @EJB
  RegistrationOracleBean registrationOracleBean;
  public StudentHomeOracleBean() {
  }

  /**
   *  Get the student grades list
   * @param studentID student id
   * @return list of grades
   */
  public ArrayList<StudentGradeModel> getStudentGradeList(Integer studentID){
    // getting the results table from db
    String query = "SELECT * FROM tblModuleResults WHERE Student_ID = " + studentID;

    // make arraylist to store info
    ArrayList student_modules = new ArrayList();

    Statement stmt = null;
    try {
      // execute query
      Connection con = oracleClientProvideBean.getOracleClient();
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()){
        // create student module grade object and enter information
        StudentGradeModel studentGrade = new StudentGradeModel();
        studentGrade.setModule_Code(getModuleCode(rs.getInt("Module_ID")));
        studentGrade.setModuleName(getModuleName(rs.getInt("Module_ID")));
        studentGrade.setGrade1(rs.getInt("Grade1"));
        studentGrade.setGrade2(rs.getInt("Grade2"));

        // add to the list of modules
        student_modules.add(studentGrade);
      }
      stmt.close();

      // return list of modules
      return student_modules;

    } catch (SQLException throwables) {
      // catch for sql errors
      throwables.printStackTrace();
    }
    return null;
  }

  /**
   * Get the module name from the module id
   * @param moduleID module id
   * @return module name
   */
  public String getModuleName(Integer moduleID){
    // Select the name from the db where the id matches
    String query = "SELECT ModuleName FROM tblModules WHERE"
            + " Module_ID = " + moduleID;

    String moduleName = null;
    Statement stmt = null;

    try {
      // execute the query
      Connection con = oracleClientProvideBean.getOracleClient();
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()){
        // set the module name into a variable
        moduleName = rs.getString("ModuleName");
      }

      stmt.close();

      // return module name
      return moduleName;

    } catch (SQLException throwables) {
      // catch for sql errors
      throwables.printStackTrace();
    }

    return null;
  }

  /**
   * Get module code from ID
   * @param moduleID module id
   * @return module code
   */
  public String getModuleCode(Integer moduleID){
    // select the code from the db where the id matches
    String query = "SELECT Module_Code FROM tblModules WHERE"
            + " Module_ID = '" + moduleID + "'";

    String moduleCode = null;
    Statement stmt = null;

    try {
      // execute query
      Connection con = oracleClientProvideBean.getOracleClient();
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()){
        // set the code as a variable
        moduleCode = rs.getString("Module_Code");
      }

      stmt.close();

      // return the variable
      return moduleCode;

    } catch (SQLException throwables) {
      // catch for sql errors
      throwables.printStackTrace();
    }

    return null;
  }

  /**
   * Get the module id from teh student number
   * @param studentID student id
   * @return module id
   */
  public Integer getModuleID(Integer studentID){
    // select the id from the db where the student id matches
    String query = "SELECT Module_ID FROM tblModuleResults WHERE"
            + " Student_ID = " + studentID ;

    Integer moduleID = null;
    Statement stmt = null;

    try {
      // execute query
      Connection con = oracleClientProvideBean.getOracleClient();
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()){
        // get the id from the db
        moduleID = Math.toIntExact(rs.getLong("Module_ID"));
      }

      stmt.close();

      // return the id
      return moduleID;

    } catch (SQLException throwables) {
      // catch for sql errors
      throwables.printStackTrace();
    }

    return null;
  }

  /**
   * Get the overall average of the student grades
   * @param grades grades
   * @return average grade
   */
  public Integer calcGrade(ArrayList<StudentGradeModel> grades){
    // query the function to gain the student average overall grade
    String SQL = "SELECT getAvg("
            + " (SELECT gradeAvg(" + grades.get(0).getGrade1() + ", " + grades.get(0).getGrade2() + ") FROM DUAL),"
            + " (SELECT gradeAvg(" + grades.get(1).getGrade1() + ", " + grades.get(1).getGrade2() + ") FROM DUAL),"
            + " (SELECT gradeAvg(" + grades.get(2).getGrade1() + ", " + grades.get(2).getGrade2() + ") FROM DUAL),"
            + " (SELECT gradeAvg(" + grades.get(3).getGrade1() + ", " + grades.get(3).getGrade2() + ") FROM DUAL),"
            + " (SELECT gradeAvg(" + grades.get(4).getGrade1() + ", " + grades.get(4).getGrade2() + ") FROM DUAL)) AS AVG1 FROM DUAL";

    Integer gradeAvg = 0;
    Statement stmt = null;

    try {
      // execute the query
      Connection con = oracleClientProvideBean.getOracleClient();
      stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(SQL);

      while (rs.next()){
        // get the average grade
        gradeAvg = rs.getInt("AVG1");
      }

      stmt.close();

      // return the average overall grade
      return gradeAvg;

    } catch (SQLException throwables) {
      // catch for sql errors
      throwables.printStackTrace();
    }

    return 0;
  }

}

