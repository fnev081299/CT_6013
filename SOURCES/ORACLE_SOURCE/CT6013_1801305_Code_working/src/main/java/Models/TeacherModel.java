package Models;

import java.io.Serializable;

/**
 * Store lecturer information
 */
public class TeacherModel implements Serializable {

    /**
     * Variables required
     */
    private long teacher_ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /**
     * Return the teacher_ID
     * @return teacher_ID
     */
    public long getTeacher_ID() {
        return teacher_ID;
    }

    /**
     * Set the teacher_ID
     * @param teacher_ID teacher_ID
     */
    public void setTeacher_ID(long teacher_ID) {
        this.teacher_ID = teacher_ID;
    }

    /**
     * Return the firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the firstName
     * @param firstName firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Return the lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the lastName
     * @param lastName lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Return the email
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
     * Return the password
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
}
