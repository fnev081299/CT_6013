package Models;

import java.io.Serializable;

/**
 * Creates a student as an object to implement into the database
 */
public class StudentModel implements Serializable{

    /**
     * Variables required
     */
    private long student_ID;
    private String student_Number;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer yearGroup;
    private Integer avgGrade;

    /**
     * Returns the student id
     * @return student id
     */
    public long getStudent_ID() {
        return student_ID;
    }

    /**
     * Set the student id
     * @param student_ID student id
     */
    public void setStudent_ID(Long student_ID) {
        this.student_ID = student_ID;
    }

    /**
     * Returns the student number
     * @return student number
     */
    public String getStudent_Number() {
        return student_Number;
    }

    /**
     * Set the student number
     * @param student_Number student number
     */
    public void setStudent_Number(String student_Number) {
        this.student_Number = student_Number;
    }

    /**
     * Returns the firstname
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the firstname
     * @param firstName firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the lastname
     * @return lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the lastname
     * @param lastName lastname
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the year group
     * @return year group
     */
    public Integer getYearGroup() {
        return yearGroup;
    }

    /**
     * Set the year group
     * @param yearGroup year group
     */
    public void setYearGroup(Integer yearGroup) {
        this.yearGroup = yearGroup;
    }

    /**
     * Returns the average grade
     * @return average grade
     */
    public Integer getAvgGrade() {
        return avgGrade;
    }

    /**
     * Set the average grade
     * @param avgGrade average grade
     */
    public void setAvgGrade(Integer avgGrade) {
        this.avgGrade = avgGrade;
    }
}
