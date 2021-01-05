package Models;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

/**
 * Stores the modules lecturers are in
 */
public class TeacherModulesModel implements Serializable {

    /**
     * Variables required
     */
    private Integer module_ID;
    private Integer teacher_ID;

    /**
     * Returns the module ID
     * @return module ID
     */
    public Integer getModuleID() {
        return module_ID;
    }

    /**
     * Sets the module ID
     * @param module_ID module ID
     */
    public void setModuleID(Integer module_ID) {
        this.module_ID = module_ID;
    }

    /**
     * Returns the teacher ID
     * @return teacher ID
     */
    public Integer getTeacherID() {
        return teacher_ID;
    }

    /**
     * Sets the teacher ID
     * @param teacher_ID teacher ID
     */
    public void setTeacherID(Integer teacher_ID) {
        this.teacher_ID = teacher_ID;
    }
}
