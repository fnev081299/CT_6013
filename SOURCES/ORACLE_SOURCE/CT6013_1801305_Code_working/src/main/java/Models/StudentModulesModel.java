package Models;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

/**
 * Stores the module grades for a student
 */
public class StudentModulesModel implements Serializable {

    /**
     * Variables required
     */
    private Integer moduleID;
    private Integer studentID;
    private Integer grade1;
    private Integer grade2;

    /**
     * Return the module ID
     * @return module ID
     */
    public Integer getModuleID() {
        return moduleID;
    }

    /**
     * Set the module ID
     * @param moduleID module ID
     */
    public void setModuleID(Integer moduleID) {
        this.moduleID = moduleID;
    }

    /**
     * Return the student id
     * @return student id
     */
    public Integer getStudentID() {
        return studentID;
    }

    /**
     * Set the student id
     * @param studentID student id
     */
    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    /**
     * Return the grade 1
     * @return grade 1
     */
    public Integer getGrade1() {
        return grade1;
    }

    /**
     * Set the grade 1
     * @param grade1 grade 1
     */
    public void setGrade1(Integer grade1) {
        this.grade1 = grade1;
    }

    /**
     * Return the grade 2
     * @return grade 2
     */
    public Integer getGrade2() {
        return grade2;
    }

    /**
     * Set the grade 2
     * @param grade2 grade 2
     */
    public void setGrade2(Integer grade2) {
        this.grade2 = grade2;
    }
}
