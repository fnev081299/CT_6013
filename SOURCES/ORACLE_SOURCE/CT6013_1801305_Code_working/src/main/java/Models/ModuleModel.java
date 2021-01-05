package Models;

import java.io.Serializable;

/**
 * Module model for the creation of modules
 */
public class ModuleModel implements Serializable {
    /**
     * Variables required
     */
    private long module_ID;
    private String module_Code;
    private String module_Name;

    private Integer yearGroup;
    private Integer passes;

    /**
     * This gest the number of passes for the module
     * @return the number of passes
     */
    public Integer getPasses() {
        return passes;
    }

    /**
     * Setting the number of passes for a module
     * @param passes passes
     */
    public void setPasses(Integer passes) {
        this.passes = passes;
    }

    /**
     * This gets the module id for referencing
     * @return the module id
     */
    public long getModule_ID() {
        return module_ID;
    }

    /**
     * Setting the module id
     * @param module_ID module id
     */
    public void setModule_ID(long module_ID) {
        this.module_ID = module_ID;
    }

    /**
     * Returns the module code
     * @return module code
     */
    public String getModule_Code() {
        return module_Code;
    }

    /**
     * sets the module code
     * @param module_Code module code
     */
    public void setModule_Code(String module_Code) {
        this.module_Code = module_Code;
    }

    /**
     * Returns the module name
     * @return module name
     */
    public String getModule_Name() {
        return module_Name;
    }

    /**
     * Sets module name
     * @param module_Name module name
     */
    public void setModule_Name(String module_Name) {
        this.module_Name = module_Name;
    }

    /**
     * Returns the module year group
     * @return year group
     */
    public Integer getYearGroup() {
        return yearGroup;
    }

    /**
     * Sets the module year group
     * @param yearGroup year group
     */
    public void setYearGroup(Integer yearGroup) {
        this.yearGroup = yearGroup;
    }
}
