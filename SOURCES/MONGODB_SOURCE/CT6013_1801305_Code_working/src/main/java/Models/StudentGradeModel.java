package Models;

import java.io.Serializable;

/**
 * Model for the student grades for a module
 */
public class StudentGradeModel implements Serializable {
    /**
     * Variables stored
     */
    private String Student_Number;
    private String Module_Code;
    private String ModuleName;
    private Integer Grade1;
    private Integer Grade2;
    private Boolean Pass;

    /**
     * Return the pass value
     * @return pass
     */
    public Boolean getPass() {
        return Pass;
    }

    /**
     * Set if student passes module
     * @param pass passes
     */
    public void setPass(Boolean pass) {
        Pass = pass;
    }

    /**
     * Return the student number
     * @return student number
     */
    public String getStudent_Number() {
        return Student_Number;
    }

    /**
     * Set the student number
     * @param student_Number student number
     */
    public void setStudent_Number(String student_Number) {
        Student_Number = student_Number;
    }

    /**
     * Return the module code
     * @return module code
     */
    public String getModule_Code() {
        return Module_Code;
    }

    /**
     * Set the module code
     * @param module_Code module code
     */
    public void setModule_Code(String module_Code) {
        Module_Code = module_Code;
    }

    /**
     * Return the module name
     * @return module name
     */
    public String getModuleName() {
        return ModuleName;
    }

    /**
     * Set the module name
     * @param moduleName module name
     */
    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    /**
     * Returns grade 1
     * @return grade 1
     */
    public Integer getGrade1() {
        return Grade1;
    }

    /**
     * Set the grade 1
     * @param grade1 grade 1
     */
    public void setGrade1(Integer grade1) {
        Grade1 = grade1;
    }

    /**
     * Returns grade 2
     * @return grade 2
     */
    public Integer getGrade2() {
        return Grade2;
    }

    /**
     * Set the grade 2
     * @param grade2 grade 2
     */
    public void setGrade2(Integer grade2) {
        Grade2 = grade2;
    }
}
